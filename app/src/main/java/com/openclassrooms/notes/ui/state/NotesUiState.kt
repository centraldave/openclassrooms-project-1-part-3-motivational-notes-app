package com.openclassrooms.notes.ui.state

import com.openclassrooms.notes.data.model.Note

data class NotesUiState(
    val notes: List<Note> = emptyList()
)
