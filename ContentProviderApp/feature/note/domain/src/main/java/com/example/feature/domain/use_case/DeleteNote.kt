package com.example.feature.domain.use_case

import com.example.feature.note.data.model.Note
import com.example.feature.note.data.repository.NoteRepository


class DeleteNote(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(note: Note){
        repository.deleteNote(note)
    }
}