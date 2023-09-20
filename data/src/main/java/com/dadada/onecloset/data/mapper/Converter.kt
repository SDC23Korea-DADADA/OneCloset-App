package com.dadada.onecloset.data.mapper

import android.content.ContentResolver
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

object Converter {

    fun createMultipartBodyPart(imagePath: String): MultipartBody.Part {
        val file = File(imagePath)
        val requestFile = file.asRequestBody("multipart/form-data".toMediaTypeOrNull())
        return requestFile.let { MultipartBody.Part.createFormData("imageFiles", file.name, it) }
    }

    fun createMultipartBodyPartOnePhoto(imagePath: String): MultipartBody.Part {
        val file = File(imagePath)
        val requestFile = file.asRequestBody("image/jpeg".toMediaTypeOrNull())
        return requestFile.let { MultipartBody.Part.createFormData("image", file.name, it) }
    }

    fun getRealPathFromUriOrNull(context: Context, uri: Uri): String? {
        val contentResolver: ContentResolver = context.contentResolver
        val cursor: Cursor? = contentResolver.query(uri, null, null, null, null)
        return if (cursor == null) {
            uri.path
        } else {
            cursor.moveToFirst()
            val index: Int = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
            val realPath: String = cursor.getString(index)
            cursor.close()
            Log.d("TEST", "getRealPathFromUriOrNull: $realPath")
            realPath
        }
    }
}