package com.example.feature.note.data.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(
        val title: String,
        val content: String,
        val timeStamp: Long,
        val color: Int ,
        @PrimaryKey val id: Int? = null
){
    companion object {
        val noteColors : List<Int> = listOf( 0xffffab91, 0xfff48fb1, 0xff81deea, 0xffcf94da, 0xffe7ed9b ).map {
            Color(it).toArgb()
        }
    }
}

class InvalidNoteException( message: String): Exception(message)
