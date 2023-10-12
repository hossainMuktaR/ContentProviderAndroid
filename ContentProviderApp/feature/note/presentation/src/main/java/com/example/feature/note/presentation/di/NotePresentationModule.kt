package com.example.feature.note.presentation.di

import com.example.feature.domain.use_case.AddNote
import com.example.feature.domain.use_case.DeleteNote
import com.example.feature.domain.use_case.GetAllNotes
import com.example.feature.domain.use_case.GetNoteById
import com.example.feature.domain.use_case.NoteUseCases
import com.example.feature.note.data.repository.NoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NotePresentationModule {
    @Provides
    @Singleton
    fun provideNoteUseCases(repository: NoteRepository): NoteUseCases {
        return NoteUseCases(
            getAllNotes = GetAllNotes(repository),
            deleteNote = DeleteNote(repository),
            addNote = AddNote(repository),
            getNoteById = GetNoteById(repository)
        )
    }
}