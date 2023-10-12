package com.example.feature.domain.note_list_redux

import android.provider.ContactsContract
import com.example.core.redux.State
import com.example.feature.note.data.model.Note
import com.example.feature.note.utils.NoteOrder
import com.example.feature.note.utils.OrderType

data class NoteListState(
    val notes: List<Note> = emptyList(),
    val noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    val isOrderSectionVisible : Boolean = false,
    val recentlyDeletedNote: Note? = null
) : State
