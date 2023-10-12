package com.example.feature.domain.use_case

import com.example.feature.note.data.model.Note
import com.example.feature.note.data.repository.NoteRepository
import com.example.feature.note.utils.NoteOrder
import com.example.feature.note.utils.OrderType


class GetAllNotes(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(
        noteOrder: NoteOrder
    ): List<Note> {
        val notes = repository.getAllNotes()
        return when (noteOrder.orderType) {
            is OrderType.Ascending -> {
                when (noteOrder) {
                    is NoteOrder.Title -> {
                        notes.sortedBy { it.title }
                    }

                    is NoteOrder.Date -> {
                        notes.sortedBy { it.timeStamp }
                    }

                    is NoteOrder.Color -> {
                        notes.sortedBy { it.color }
                    }
                }
            }

            is OrderType.Descending -> {
                when (noteOrder) {
                    is NoteOrder.Title -> {
                        notes.sortedByDescending { it.title }
                    }

                    is NoteOrder.Date -> {
                        notes.sortedByDescending { it.timeStamp }
                    }

                    is NoteOrder.Color -> {
                        notes.sortedByDescending { it.color }
                    }
                }
            }
        }
    }
}