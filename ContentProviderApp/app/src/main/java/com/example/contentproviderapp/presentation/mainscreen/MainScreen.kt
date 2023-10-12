package com.example.contentresolverapp.presentation.mainscreen


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.contentresolverapp.presentation.mainscreen.components.NoteItem
import com.example.feature.note.data.model.Note

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScrren(
    navController: NavController,
    notes: List<Note>
) {

    val hostState = remember {
        SnackbarHostState()
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                          //TODO: NEED TO CODE
                },
                containerColor = MaterialTheme.colorScheme.primary,
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Note")
            }
        },
        snackbarHost = { SnackbarHost(hostState = hostState) }
    ) { paddingValues ->

        if(notes.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                Text("No Note Found. Have some issue")
            }
        }else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp)
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(notes) { note ->
                        NoteItem(
                            note = note,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
//                                viewModel.onNoteClicked(note)
                                }
                                .padding(bottom = 8.dp),
                            onDeleteClick = {
//                            viewModel.deleteNote(note)
                            }
                        )
                    }
                }
            }
        }
    }
}
