package com.example.feature.domain.use_case

import com.example.feature.note.data.repository.NoteRepository
import com.example.feature.note.data.model.InvalidNoteException
import com.example.feature.note.data.model.Note

class AddNote(
    private val repository: NoteRepository
) {

    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note: Note) {
        if (note.title.isBlank()){
            throw InvalidNoteException("The title of the note are empty")
        }
        if (note.content.isBlank()){
            throw InvalidNoteException("The content of the note are empty")
        }
        repository.insertNote(note)
    }
}