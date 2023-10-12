package com.example.feature.note.data.repository

import com.example.feature.note.data.model.Note


interface NoteRepository {

    suspend fun getAllNotes(): List<Note>

    suspend fun getNoteById(id: Int): Note?

    suspend fun insertNote(note: Note)

    suspend fun deleteNote(note: Note)
}