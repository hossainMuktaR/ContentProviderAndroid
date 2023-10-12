package com.example.feature.domain.note_list_redux

import com.example.core.redux.Container
import com.example.core.redux.Middleware

class NoteListEffectMiddleware :
    Middleware<NoteListAction, NoteListState, NoteListSideEffect> {
    override suspend fun process(
        action: NoteListAction,
        currentState: NoteListState,
        container: Container<NoteListAction, NoteListState, NoteListSideEffect>
    ) {
        when (action){
            NoteListAction.GoAddEditScreen -> {
                container.emitSideEffect(NoteListSideEffect.NavigateAddNoteScreen)
            }
            is NoteListAction.GoNoteUpdateScreen -> {
                container.emitSideEffect(NoteListSideEffect.GoNoteEditScreen(action.noteId, action.noteColor))
            }
            else -> { }
        }
    }

}