package com.example.feature.domain.add_edit_note_redux

import com.example.core.redux.BaseContainer
import com.example.feature.domain.use_case.NoteUseCases

class AddEditNoteContainer(
    useCases: NoteUseCases
): BaseContainer<AddEditNoteAction, AddEditNoteState, AddEditNoteSideEffect>(
    initialState = AddEditNoteState(),
    reducer = AddEditNoteReducer(),
    middleware = listOf(
        AddEditEffectMiddleware(),
        NoteSaveMiddleware(useCases)
    ),
)