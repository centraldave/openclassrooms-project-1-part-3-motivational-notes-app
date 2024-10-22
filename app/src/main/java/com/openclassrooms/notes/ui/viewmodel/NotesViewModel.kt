package com.openclassrooms.notes.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.openclassrooms.notes.data.repository.NotesRepository
import com.openclassrooms.notes.ui.state.NotesUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class NotesViewModel: ViewModel() {
    private val notesRepository: NotesRepository = NotesRepository()
    private val _uiState = MutableStateFlow(NotesUiState())
    val uiState: StateFlow<NotesUiState> = _uiState.asStateFlow()

    init {
        getNotes()
    }

    fun getNotes() {
        viewModelScope.launch {
            notesRepository.notes.collect { notesList ->
                _uiState.value = NotesUiState(
                    notes = notesList
                )
            }
        }
    }
}