package com.openclassrooms.notes

import com.openclassrooms.notes.data.repository.NotesRepository
import com.openclassrooms.notes.data.service.LocalNotesApiService
import com.openclassrooms.notes.data.service.NotesApiService
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class NotesRepositoryUnitTest {

    private lateinit var notesRepository: NotesRepository

    @Before
    fun setUp() {
        notesRepository = NotesRepository()
    }

    @Test
    fun testNotesFlowWhenNotesRepositoryEmits() = runBlocking {
        // given
        val localApiService = LocalNotesApiService()
        val expected = localApiService.getAllNotes()
        // when
        val actual = notesRepository.notes.first()
        // then
        assertEquals(expected, actual)
    }
}