package com.openclassrooms.notes

import com.openclassrooms.notes.data.model.Note
import com.openclassrooms.notes.data.service.LocalNotesApiService
import com.openclassrooms.notes.ui.viewmodel.NotesViewModel
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class NotesViewModelTest {

    private val notesViewModel = NotesViewModel()
    private val localNotesApiService = LocalNotesApiService()

    @Test
    fun notesViewModel_GetNotes_NotesUpdated() = runTest {
        // given
        notesViewModel.getNotes()
        // when
        val currentNotesUiState = notesViewModel.uiState.value
        val expectedNotes: List<Note> = localNotesApiService.getAllNotes()
        // then
        assertEquals(expectedNotes.size, currentNotesUiState.notes.size)
    }
}