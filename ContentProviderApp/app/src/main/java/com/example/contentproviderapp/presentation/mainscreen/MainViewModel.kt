package com.example.contentresolverapp.presentation.mainscreen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.feature.note.data.model.Note

class MainViewModel: ViewModel() {
    
    private val _noteList = mutableStateOf<List<Note>>(emptyList())
    val noteList: State<List<Note>> = _noteList

    fun updateList(noteList: List<Note>) {
        _noteList.value = noteList
        Log.i("mainModel","updateList call")
    }
}