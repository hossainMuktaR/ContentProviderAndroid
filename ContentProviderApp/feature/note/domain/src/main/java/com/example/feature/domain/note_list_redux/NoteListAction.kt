package com.example.feature.domain.note_list_redux

import com.example.core.redux.Action
import com.example.feature.note.data.model.Note
import com.example.feature.note.utils.NoteOrder

sealed class NoteListAction: Action {
    data class AllNoteLoaded(val notes: List<Note>, val order: NoteOrder): NoteListAction()
    data class FetchOrderedNoteList(val order: NoteOrder): NoteListAction()
    data class DeleteNote(val note: Note, val order: NoteOrder): NoteListAction()
    data class RestoreNote(val note: Note, val order: NoteOrder): NoteListAction()
    object ToggleOrderSection: NoteListAction()
    data class GoNoteUpdateScreen(val noteId: Int, val noteColor: Int): NoteListAction()
    object GoAddEditScreen: NoteListAction()
}
