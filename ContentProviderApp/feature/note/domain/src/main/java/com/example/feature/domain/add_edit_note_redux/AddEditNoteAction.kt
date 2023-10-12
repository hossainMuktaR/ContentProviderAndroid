package com.example.feature.domain.add_edit_note_redux

import androidx.compose.ui.focus.FocusState
import com.example.core.redux.Action
import com.example.feature.note.data.model.Note

sealed class AddEditNoteAction: Action {
    data class TitleChanged(val value: String): AddEditNoteAction()
    data class TitleFocusChanged(val focusState: FocusState, val titleValue: String): AddEditNoteAction()
    data class ContentChanged(val value: String): AddEditNoteAction()
    data class ContentFocusChanged(val focusState: FocusState,val contentValue: String ): AddEditNoteAction()
    data class ColorChanged(val color: Int): AddEditNoteAction()
    object SaveNote: AddEditNoteAction()
    data class FetchNoteById(val noteId: Int): AddEditNoteAction()
    data class FetchNoteComplated(val note: Note): AddEditNoteAction()
}
