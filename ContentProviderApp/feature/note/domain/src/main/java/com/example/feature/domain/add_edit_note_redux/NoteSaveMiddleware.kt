package com.example.feature.domain.add_edit_note_redux

import com.example.core.redux.Container
import com.example.core.redux.Middleware
import com.example.feature.domain.use_case.NoteUseCases
import com.example.feature.note.data.model.InvalidNoteException
import com.example.feature.note.data.model.Note

class NoteSaveMiddleware(
    private val noteUseCases: NoteUseCases
): Middleware<AddEditNoteAction, AddEditNoteState, AddEditNoteSideEffect> {
    override suspend fun process(
        action: AddEditNoteAction,
        currentState: AddEditNoteState,
        container: Container<AddEditNoteAction, AddEditNoteState, AddEditNoteSideEffect>
    ) {
        when(action) {
            AddEditNoteAction.SaveNote -> {
                val note = Note(
                    title = currentState.noteTitle,
                    content = currentState.noteContent,
                    color = currentState.noteColor,
                    timeStamp = System.currentTimeMillis(),
                    id = currentState.currentNoteid
                )
                try {
                    noteUseCases.addNote(note)
                    container.emitSideEffect(AddEditNoteSideEffect.SaveNote)
                } catch (e: InvalidNoteException){
                    container.emitSideEffect(AddEditNoteSideEffect.ShowSnackbar(
                        message = e.message ?: "Couldn't Save Note"
                    ))
                }
            }
            is AddEditNoteAction.FetchNoteById -> {
                noteUseCases.getNoteById(action.noteId)?.also { note ->
                    container.dispatch(AddEditNoteAction.FetchNoteComplated(note))
                }

            }
            else -> { }
        }
    }

}