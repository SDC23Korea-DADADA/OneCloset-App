package com.dadada.onecloset.data.repository

import android.content.Context
import android.graphics.BitmapFactory
import android.net.Uri
import com.dadada.onecloset.data.datasource.remote.ClosetService
import com.dadada.onecloset.data.datasource.remote.handleApi
import com.dadada.onecloset.data.mapper.Converter
import com.dadada.onecloset.data.mapper.toDomain
import com.dadada.onecloset.domain.model.Closet
import com.dadada.onecloset.domain.model.ClothAnalysis
import com.dadada.onecloset.domain.model.ClothCareCourse
import com.dadada.onecloset.domain.model.NetworkResult
import com.dadada.onecloset.domain.model.clothes.ClothesInfo
import com.dadada.onecloset.domain.repository.ClosetRepository
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.ByteArrayOutputStream
import javax.inject.Inject

private const val TAG = "ClosetRepositoryImpl"

class ClosetRepositoryImpl @Inject constructor(
    private val closetService: ClosetService,
    private val context: Context,
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

    override suspend fun getBasicClothList(): NetworkResult<List<ClothesInfo>> {
        return handleApi { closetService.getBasicClothList().toDomain() }
    }

    override suspend fun getClothList(id: String): NetworkResult<List<ClothesInfo>> {
        return handleApi { closetService.getClothList(id).toDomain() }
    }

    override suspend fun putCloth(clothesInfoRequest: ClothesInfo): NetworkResult<Long> {
        return handleApi { closetService.putCloth(clothesInfoRequest).toDomain() }
    }

    override suspend fun getCloth(id: String): NetworkResult<ClothesInfo> {
        return handleApi { closetService.getCloth(id).toDomain() }
    }

    override suspend fun deleteCloth(id: String): NetworkResult<Unit> {
        return handleApi { closetService.deleteCloth(id) }
    }

    override suspend fun getClothAnalysis(image: String): NetworkResult<ClothAnalysis> {
        return handleApi {
            closetService.putAnalysisImage(
                Converter.createMultipartBodyPart(
                    context,
                    image,
                ),
            ).toDomain()
        }
    }

    override suspend fun getClothCareCourse(material: String): NetworkResult<ClothCareCourse> {
        return handleApi { closetService.getClothCareCourse(material).toDomain() }
    }

    override suspend fun updateClothes(clothesInfo: ClothesInfo): NetworkResult<Unit> {
        return handleApi { closetService.updateClothes(clothesInfo) }
    }

    override suspend fun checkClothes(image: String): NetworkResult<Boolean> {
        return handleApi {
            closetService.checkClothes(
                Converter.createMultipartBodyPart(
                    context,
                    image,
                ),
            ).data
        }
    }
}
