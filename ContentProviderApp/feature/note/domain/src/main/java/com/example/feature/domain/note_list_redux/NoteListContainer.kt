package com.example.feature.domain.note_list_redux

import com.example.core.redux.BaseContainer
import com.example.feature.domain.use_case.NoteUseCases

class NoteListContainer(
    noteUseCases: NoteUseCases
): BaseContainer<NoteListAction, NoteListState, NoteListSideEffect>(
    initialState = NoteListState(),
    reducer = NoteListReducer(),
    middleware = listOf(
        NoteListEffectMiddleware(),
        NoteLocalDataMiddleware(noteUseCases)
    ),
)