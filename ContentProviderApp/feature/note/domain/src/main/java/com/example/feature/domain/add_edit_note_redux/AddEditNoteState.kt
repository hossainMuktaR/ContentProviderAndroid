package com.example.feature.domain.add_edit_note_redux

import androidx.compose.ui.graphics.toArgb
import com.example.core.redux.State
import com.example.feature.note.data.model.Note

data class AddEditNoteState(
    val noteTitle: String = "",
    val titleHint: String = "Enter title here...",
    val isTitleHintVisible: Boolean = true,
    val noteContent: String = "",
    val contentHint: String = "Enter Note Content Here.....",
    val isContentHintVisible: Boolean = true,
    val noteColor: Int = Note.noteColors.random(),
    val errorMessage: String? = null,
    val currentNoteid: Int? = null,
): State
