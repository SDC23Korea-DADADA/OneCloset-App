package com.dadada.onecloset.data.mapper

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.net.Uri
import android.provider.MediaStore
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.ByteArrayOutputStream

object Converter {

    fun createMultipartBodyPart(context: Context, imagePath: String): MultipartBody.Part {
        val inputStream = context.contentResolver.openInputStream(Uri.parse(imagePath))
        var bitmap = BitmapFactory.decodeStream(inputStream)

        // 이미지의 Orientation 정보를 얻어옴
        val orientation = getOrientation(context, Uri.parse(imagePath))

        // 이미지를 적절히 회전
        bitmap = rotateBitmap(bitmap, orientation)

        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val byteArray = baos.toByteArray()

        val imageRequestBody = RequestBody.create("image/jpeg".toMediaTypeOrNull(), byteArray)

        return MultipartBody.Part.createFormData("image", "filename.jpg", imageRequestBody)
    }

    fun getOrientation(context: Context, photoUri: Uri): Int {
        val cursor = context.contentResolver.query(
            photoUri,
            arrayOf(MediaStore.Images.ImageColumns.ORIENTATION),
            null,
            null,
            null,
        )
        if (cursor == null || cursor.count != 1) {
            cursor?.close()
            return -1
        }

        cursor.moveToFirst()
        val orientation = cursor.getInt(0)
        cursor.close()
        return orientation
    }

    fun rotateBitmap(bitmap: Bitmap, orientation: Int): Bitmap {
        val matrix = Matrix()
        when (orientation) {
            90 -> matrix.postRotate(90f)
            180 -> matrix.postRotate(180f)
            270 -> matrix.postRotate(270f)
        }
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
    }
}
