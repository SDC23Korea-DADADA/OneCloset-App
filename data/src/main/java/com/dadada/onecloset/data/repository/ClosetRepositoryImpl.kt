package com.dadada.onecloset.data.repository

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.core.net.toUri
import com.dadada.onecloset.data.datasource.remote.ClosetService
import com.dadada.onecloset.data.datasource.remote.handleApi
import com.dadada.onecloset.data.mapper.Converter
import com.dadada.onecloset.data.mapper.toDomain
import com.dadada.onecloset.domain.model.Closet
import com.dadada.onecloset.domain.model.Cloth
import com.dadada.onecloset.domain.model.ClothAnalysis
import com.dadada.onecloset.domain.model.NetworkResult
import com.dadada.onecloset.domain.repository.ClosetRepository
import com.google.gson.Gson
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.ByteArrayOutputStream
import javax.inject.Inject

private const val TAG = "ClosetRepositoryImpl"

class ClosetRepositoryImpl @Inject constructor(
    private val closetService: ClosetService,
    private val context: Context
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

    override suspend fun getBasicClothList(): NetworkResult<List<Cloth>> {
        return handleApi { closetService.getBasicClothList().toDomain() }
    }


    override suspend fun getClothList(id: String): NetworkResult<List<Cloth>> {
        return handleApi { closetService.getClothList(id).toDomain() }
    }

    override suspend fun putCloth(cloth: Cloth): NetworkResult<Long> {
        val inputStream = context.contentResolver.openInputStream(Uri.parse(cloth.img))
        val bitmap = BitmapFactory.decodeStream(inputStream)
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos)
        val byteArray = baos.toByteArray()

        val descriptionRequestBody = MultipartBody.Part.createFormData("description", cloth.description)
        val colorCodeRequestBody = MultipartBody.Part.createFormData("colorCode", cloth.colorCode)
        val materialRequestBody = MultipartBody.Part.createFormData("material", cloth.material)
        val typeRequestBody = MultipartBody.Part.createFormData("type", cloth.type)
        val closetIdRequestBody = MultipartBody.Part.createFormData("closetId", cloth.closetId)
        val imageRequestBody = RequestBody.create("image/jpeg".toMediaTypeOrNull(), byteArray)
        val imagePart = MultipartBody.Part.createFormData("image", "filename.jpg", imageRequestBody)


        val parts = MultipartBody.Part.createFormData("hashtagList", "")
        val parts2 = MultipartBody.Part.createFormData("weatherList", "")
        val parts3 = MultipartBody.Part.createFormData("tpoList", "")



        return handleApi {
            closetService.putCloth(
                imagePart, typeRequestBody, colorCodeRequestBody, materialRequestBody, descriptionRequestBody,
                parts, parts2, parts3, closetIdRequestBody
            ).toDomain()
        }
    }

    override suspend fun getCloth(id: String): NetworkResult<Cloth> {
        return handleApi { closetService.getCloth(id).toDomain() }
    }

    override suspend fun deleteCloth(id: String): NetworkResult<Unit> {
        return handleApi { closetService.deleteCloth(id) }
    }

    override suspend fun getClothAnalysis(image: String): NetworkResult<ClothAnalysis> {
        val inputStream = context.contentResolver.openInputStream(Uri.parse(image))
        val bitmap = BitmapFactory.decodeStream(inputStream)
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos)
        val byteArray = baos.toByteArray()

        val imageRequestBody = RequestBody.create("image/jpeg".toMediaTypeOrNull(), byteArray)
        val imagePart = MultipartBody.Part.createFormData("image", "filename.jpg", imageRequestBody)


        return handleApi { closetService.putAnalysisImage(imagePart).toDomain() }
    }
}