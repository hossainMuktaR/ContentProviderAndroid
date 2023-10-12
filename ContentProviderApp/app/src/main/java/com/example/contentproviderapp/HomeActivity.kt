package com.example.contentproviderapp

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.contentproviderapp.noteprovider.NoteProviderContract
import com.example.contentproviderapp.ui.theme.ContentProviderAppTheme
import com.example.contentresolverapp.presentation.mainscreen.MainScrren
import com.example.contentresolverapp.presentation.mainscreen.MainViewModel
import com.example.feature.note.data.model.Note

class HomeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val vm = viewModels<MainViewModel>().value
        Log.i("mainModel", "onCreate Call")
        val cursor = contentResolver.query(
            NoteProviderContract.GETALLNOTES_CONTENT_URI,
            null,
            null,
            null,
            null
        )
        when(cursor?.count) {
            null -> {
                Log.i("mainModel", "cursor null")
            }
            0 -> {
                Log.i("mainModel", "cursor 0")
            }
            else -> {
                Log.i("mainModel", "cursor found")
                val idColumn = cursor.getColumnIndex(NoteProviderContract.NoteColumnid)
                val titleColumn = cursor.getColumnIndex(NoteProviderContract.NoteColumnTitle)
                val contentColumn = cursor.getColumnIndex(NoteProviderContract.NoteColumncontent)
                val timeStampColumn = cursor.getColumnIndex(NoteProviderContract.NoteColumntimeStamp)
                val colorColumn = cursor.getColumnIndex(NoteProviderContract.NoteColumncolor)
                val noteList = mutableListOf<Note>()
                while (cursor.moveToNext()) {
                    val note = Note(
                        id = cursor.getInt(idColumn),
                        title = cursor.getString(titleColumn),
                        content = cursor.getString(contentColumn),
                        timeStamp = cursor.getLong(timeStampColumn),
                        color = cursor.getInt(colorColumn)
                    )
                    noteList.add(note)
                }
                vm.updateList(noteList)
            }
        }

        setContent {
            ContentProviderAppTheme{
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "mainScreen") {
                        composable("mainScreen") {
                            MainScrren(navController = navController, notes = vm.noteList.value)
                        }
                    }
                }
            }
        }
    }
}
