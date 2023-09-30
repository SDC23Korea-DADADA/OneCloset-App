package com.dadada.onecloset.presentation.ui.photo.datasource

import android.content.ContentUris
import android.content.ContentValues
import android.content.Context
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import androidx.annotation.RequiresApi
import java.io.File
import java.util.UUID

class FileDataSource {

    private val externalDir = "${Environment.DIRECTORY_DCIM}${File.separator}$RELATIVE_PATH"

    private val currentFileName: String
        get() = "${System.currentTimeMillis()}-${UUID.randomUUID()}"

    private val externalStorage
        get() = Environment.getExternalStoragePublicDirectory(externalDir).apply { mkdirs() }

    val externalFiles
        get() = externalStorage.listFiles()?.sortedByDescending { it.lastModified() }

    val lastPicture get() = externalFiles?.firstOrNull()

    fun getFile(
        extension: String = "jpg",
    ): File = File(externalStorage.path, "$currentFileName.$extension").apply {
        if (parentFile?.exists() == false) parentFile?.mkdirs()
        createNewFile()
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    val imageContentValues: ContentValues = getContentValues(JPEG_MIME_TYPE)

    @RequiresApi(Build.VERSION_CODES.Q)
    val videoContentValues: ContentValues = getContentValues(VIDEO_MIME_TYPE)

    @RequiresApi(Build.VERSION_CODES.Q)
    private fun getContentValues(mimeType: String) = ContentValues().apply {
        put(MediaStore.MediaColumns.DISPLAY_NAME, currentFileName)
        put(MediaStore.MediaColumns.MIME_TYPE, mimeType)
        put(MediaStore.MediaColumns.RELATIVE_PATH, externalDir)
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    fun getLastPictureUriPostQ(context: Context): Uri? {
        val projection = arrayOf(MediaStore.Images.Media._ID)
        val selection = "${MediaStore.Images.Media.DISPLAY_NAME}=?"
        val selectionArgs = arrayOf(lastPicture?.name)

        context.contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            projection,
            selection,
            selectionArgs,
            null
        )?.use { cursor ->
            if (cursor.moveToFirst()) {
                val id = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID))
                return ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id)
            }
        }
        return null
    }


    companion object {
        private const val JPEG_MIME_TYPE = "image/jpeg"
        private const val VIDEO_MIME_TYPE = "video/mp4"
        private const val RELATIVE_PATH = "Camposer"
    }
}
