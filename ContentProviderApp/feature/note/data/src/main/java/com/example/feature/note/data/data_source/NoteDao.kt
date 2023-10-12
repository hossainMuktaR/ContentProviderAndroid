package com.example.feature.note.data.data_source

import android.database.Cursor
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.feature.note.data.model.Note

@Dao
interface NoteDao {

    @Query("SELECT * FROM note")
    suspend fun getAllNotes(): List<Note>

    @Query("SELECT * FROM note WHERE id = :id")
    suspend fun getNoteById(id: Int): Note?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    //    for provider
    @Query("SELECT * FROM note")
    fun getAllNotesForProvider(): Cursor

    @Query("SELECT * FROM note WHERE id = :id")
    fun getNoteByIdForProvider(id: Int): Cursor

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNoteForProvider(note: Note): Long

    @Query("DELETE FROM note WHERE id = :id")
    suspend fun deleteNoteForProvider(id: Int): Int
}