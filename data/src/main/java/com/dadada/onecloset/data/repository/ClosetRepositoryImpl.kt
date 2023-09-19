package com.dadada.onecloset.data.repository

import android.content.ContentResolver
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.core.net.toUri
import com.dadada.onecloset.data.datasource.remote.ClosetService
import com.dadada.onecloset.data.datasource.remote.handleApi
import com.dadada.onecloset.data.mapper.toDomain
import com.dadada.onecloset.domain.model.Closet
import com.dadada.onecloset.domain.model.Cloth
import com.dadada.onecloset.domain.model.NetworkResult
import com.dadada.onecloset.domain.repository.ClosetRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.ByteArrayOutputStream
import javax.inject.Inject

private const val TAG = "ClosetRepositoryImpl"

class ClosetRepositoryImpl @Inject constructor(
    private val closetService: ClosetService,
) : ClosetRepository {
    override suspend fun getClosetList(): NetworkResult<List<Closet>> {
        return handleApi { closetService.getClosetList().toDomain() }
    }

    override suspend fun putCloset(closet: Closet): NetworkResult<Unit> {
        return handleApi { closetService.putCloset(closet) }
    }

    override suspend fun updateCloset(closet: Closet): NetworkResult<Unit> {
        return handleApi { closetService.updateCloset(closet) }
    }

    override suspend fun deleteCloset(id: String): NetworkResult<Unit> {
        return handleApi { closetService.deleteCloset(id) }
    }

    override suspend fun getClothList(id: String): NetworkResult<List<Cloth>> {
        return handleApi { closetService.getClothList(id).toDomain() }
    }

    override suspend fun putCloth(cloth: Cloth): NetworkResult<Unit> {
//        val inputStream = context.contentResolver.openInputStream(cloth.img.toUri())
//        val bitmap = BitmapFactory.decodeStream(inputStream)
//        val baos = ByteArrayOutputStream()
//        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
//        val byteArray = baos.toByteArray()


        val requestBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("laundry", cloth.laundry)
            .addFormDataPart("dryer", cloth.dryer)
            .addFormDataPart("airDressor", cloth.airDressor)
            .addFormDataPart("colorCode", cloth.colorCode)
            .addFormDataPart("material", cloth.material)
            .addFormDataPart("type", cloth.type)
            .addFormDataPart("closetId", cloth.closetId.toString()) // 숫자를 문자열로 변환
//            .addFormDataPart("img", "filename.jpg",
//                RequestBody.create("image/jpeg".toMediaTypeOrNull(), byteArray)) // 이미지 추가
            .build()
        return handleApi { closetService.putCloth(requestBody) }
    }

    override suspend fun getCloth(id: String): NetworkResult<Cloth> {
        return handleApi { closetService.getCloth(id).toDomain() }
    }
}