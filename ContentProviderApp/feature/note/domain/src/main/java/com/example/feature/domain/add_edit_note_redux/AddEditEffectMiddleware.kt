package com.example.feature.domain.add_edit_note_redux

import com.example.core.redux.Container
import com.example.core.redux.Middleware


class AddEditEffectMiddleware:
    Middleware<AddEditNoteAction, AddEditNoteState, AddEditNoteSideEffect> {
    override suspend fun process(
        action: AddEditNoteAction,
        currentState: AddEditNoteState,
        container: Container<AddEditNoteAction, AddEditNoteState, AddEditNoteSideEffect>
    ) {
        when(action) {

            else -> { }
        }
    }
}
