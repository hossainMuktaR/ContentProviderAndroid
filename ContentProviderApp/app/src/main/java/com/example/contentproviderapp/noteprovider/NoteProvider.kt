package com.example.contentproviderapp.noteprovider

import android.content.ContentProvider
import android.content.ContentUris
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.internal.illegalDecoyCallException
import com.example.contentproviderapp.noteprovider.NoteProviderContract.AUTHORITY
import com.example.contentproviderapp.noteprovider.NoteProviderContract.MIMETYPE_GETALLNOTES
import com.example.contentproviderapp.noteprovider.NoteProviderContract.MIMETYPE_GETNOTEBYID
import com.example.contentproviderapp.noteprovider.NoteProviderContract.PATH_GETALLNOTES
import com.example.contentproviderapp.noteprovider.NoteProviderContract.PATH_GETNOTEBYID
import com.example.feature.note.data.data_source.NoteDao
import com.example.feature.note.data.data_source.NoteDatabase
import com.example.feature.note.data.model.Note
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlin.coroutines.coroutineContext
import kotlin.jvm.Throws

class NoteProvider : ContentProvider() {

    private lateinit var noteDao: NoteDao



    private val GETALLNOTES = 1
    private val GETNOTEBYID = 2

    private val uriMatcher = UriMatcher(UriMatcher.NO_MATCH).apply {
        addURI(AUTHORITY, PATH_GETALLNOTES, GETALLNOTES)
        addURI(AUTHORITY, PATH_GETNOTEBYID, GETNOTEBYID)
    }

    override fun onCreate(): Boolean {
        noteDao = NoteDatabase.getNoteDatabase(context!!.applicationContext).noteDao
        Log.i("mainModel", "provider database created")
        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? = runBlocking {
        var cursor: Cursor? = null
        Log.i("mainModel", "query called")
        when (uriMatcher.match(uri)) {
            GETALLNOTES -> {
                Log.i("mainModel", "request comes")
                runWithIO {
                    cursor = noteDao.getAllNotesForProvider()
                }
            }

            GETNOTEBYID -> {
                val id = uri.lastPathSegment?.toInt()
                runWithIO {
                    cursor = id?.let { noteDao.getNoteByIdForProvider(it) }
                }
            }
            else -> cursor
        }
        return@runBlocking cursor
    }

    override fun getType(uri: Uri): String? {
        return when (uriMatcher.match(uri)) {
            GETALLNOTES -> MIMETYPE_GETALLNOTES
            GETNOTEBYID -> MIMETYPE_GETNOTEBYID
            else -> null
        }
    }

    @Throws(IllegalArgumentException::class)
    override fun insert(uri: Uri, values: ContentValues?): Uri? = runBlocking {
        when (uriMatcher.match(uri)) {
            GETALLNOTES -> {
                val note = Note(
                    title = values!!.getAsString("title"),
                    content = values.getAsString("content"),
                    timeStamp = values.getAsLong("timeStamp"),
                    color = values.getAsInteger("color")
                )
                val id = noteDao.insertNoteForProvider(note)
                context?.contentResolver?.notifyChange(uri, null)
                return@runBlocking ContentUris.withAppendedId(uri, id)
            }

            else -> {
                throw illegalDecoyCallException("Note insertion operation failed")
            }
        }
        return@runBlocking null
    }

    @Throws(IllegalArgumentException::class)
    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int = runBlocking {
        when (uriMatcher.match(uri)) {
            GETNOTEBYID -> {
                val id = uri.lastPathSegment?.toInt()
                return@runBlocking noteDao.deleteNoteForProvider(id!!)
            }

            else -> {
                return@runBlocking -1
            }
        }
    }

    @Throws(IllegalArgumentException::class)
    override fun update(
        uri: Uri,
        contentValues: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        throw IllegalArgumentException("Not implemented yet")
    }

    private suspend fun runWithIO(transForm: () -> Unit){
        withContext(Dispatchers.IO) {
            transForm.invoke()
        }
    }
}