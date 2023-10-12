package com.example.contentproviderapp.noteprovider

import android.content.ContentResolver
import android.net.Uri

object NoteProviderContract {
    private const val packgeName = "com.example.contentproviderapp"
    const val AUTHORITY = "$packgeName.noteprovider"

    const val PATH_GETALLNOTES = "note"
    const val PATH_GETNOTEBYID = "note/#"

    const val MIMETYPE_GETALLNOTES =
        "${ContentResolver.CURSOR_DIR_BASE_TYPE}/vnd.$packgeName.note"
    const val MIMETYPE_GETNOTEBYID =
        "${ContentResolver.CURSOR_ITEM_BASE_TYPE}/vnd.$packgeName.note"

    val GETALLNOTES_CONTENT_URI = Uri.parse("content://$AUTHORITY/$PATH_GETALLNOTES")
    val GETNOTEBYID_CONTENT_URI = Uri.parse("content://$AUTHORITY/$PATH_GETNOTEBYID")

    const val NoteColumnTitle = "title"
    const val NoteColumncontent = "content"
    const val NoteColumntimeStamp = "timeStamp"
    const val NoteColumncolor = "color"
    const val NoteColumnid = "id"

    const val NoteContentProviderPermission = "com.example.contentproviderapp.READ_WRITE_PERMISSION"

}