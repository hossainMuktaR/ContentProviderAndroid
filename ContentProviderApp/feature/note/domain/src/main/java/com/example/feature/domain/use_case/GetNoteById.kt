package com.example.feature.domain.use_case

import com.example.feature.note.data.model.Note
import com.example.feature.note.data.repository.NoteRepository


class GetNoteById(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(id: Int): Note? {
        return repository.getNoteById(id)
    }
}