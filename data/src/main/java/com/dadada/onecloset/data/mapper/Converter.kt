package com.dadada.onecloset.data.mapper

import android.content.ContentResolver
import android.content.Context
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.ByteArrayOutputStream
import java.io.File

object Converter {


    fun createMultipartBodyPart(context: Context, imagePath: String): MultipartBody.Part {
        val inputStream = context.contentResolver.openInputStream(Uri.parse(imagePath))
        val bitmap = BitmapFactory.decodeStream(inputStream)
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos)
        val byteArray = baos.toByteArray()

        val imageRequestBody = RequestBody.create("image/jpeg".toMediaTypeOrNull(), byteArray)

        return MultipartBody.Part.createFormData("image", "filename.jpg", imageRequestBody)
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