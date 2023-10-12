package com.example.contentresolverapp

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
import androidx.core.content.ContextCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.contentresolverapp.data.Note
import com.example.contentresolverapp.data.NoteContract
import com.example.contentresolverapp.presentation.mainscreen.MainScrren
import com.example.contentresolverapp.presentation.mainscreen.MainViewModel
import com.example.contentresolverapp.ui.theme.ContentResolverAppTheme

class HomeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (ActivityCompat.checkSelfPermission(
                this,
                NoteContract.NoteContentProviderPermission
            ) == PackageManager.PERMISSION_DENIED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(NoteContract.NoteContentProviderPermission),
                0
            )
            Log.i("mainModel", "Permission not Granted")
        } else {
            Log.i("mainModel", "Permission Granted")
        }
        val vm = viewModels<MainViewModel>().value
        Log.i("mainModel", "onCreate Call")
        val cursor = contentResolver.query(
            NoteContract.GETALLNOTES_CONTENT_URI,
            null,
            null,
            null,
            null
        )
        when (cursor?.count) {
            null -> {
                Log.i("mainModel", "cursor null")
            }

            0 -> {
                Log.i("mainModel", "cursor 0")
            }

            else -> {
                Log.i("mainModel", "cursor found")
                val idColumn = cursor.getColumnIndex(NoteContract.NoteColumnid)
                val titleColumn = cursor.getColumnIndex(NoteContract.NoteColumnTitle)
                val contentColumn = cursor.getColumnIndex(NoteContract.NoteColumncontent)
                val timeStampColumn = cursor.getColumnIndex(NoteContract.NoteColumntimeStamp)
                val colorColumn = cursor.getColumnIndex(NoteContract.NoteColumncolor)
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
            ContentResolverAppTheme {
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
