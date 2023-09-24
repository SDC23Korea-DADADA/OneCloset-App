package com.dadada.onecloset.data.repository

import android.content.Context
import com.dadada.onecloset.data.datasource.remote.CodiService
import com.dadada.onecloset.data.datasource.remote.handleApi
import com.dadada.onecloset.data.mapper.Converter
import com.dadada.onecloset.data.mapper.toDomain
import com.dadada.onecloset.domain.model.NetworkResult
import com.dadada.onecloset.domain.model.codi.CodiRegisterInfo
import com.dadada.onecloset.domain.model.codi.CodiUpdateDate
import com.dadada.onecloset.domain.repository.CodiRepository
import com.google.gson.Gson
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import javax.inject.Inject

class CodiRepositoryImpl @Inject constructor(
    private val codiService: CodiService,
    private val context: Context
) : CodiRepository {
    override suspend fun putCodi(imagePath: String, info: CodiRegisterInfo): NetworkResult<Long> {
        val requestDto = RequestBody.create("application/json".toMediaTypeOrNull(), Gson().toJson(info))
        return handleApi { codiService.putCodi(Converter.createMultipartBodyPart(context, imagePath), requestDto).toDomain()}
    }

    override suspend fun updateCodi(imagePath: String, info: CodiRegisterInfo): NetworkResult<Unit> {
        val requestDto = RequestBody.create("application/json".toMediaTypeOrNull(), Gson().toJson(info))
        return handleApi { codiService.updateCodi(Converter.createMultipartBodyPart(context, imagePath), requestDto) }
    }

    override suspend fun deleteCodi(id: String): NetworkResult<Unit> {
        return handleApi { codiService.deleteCodi(id) }
    }

    override suspend fun updateCodiDate(info: CodiUpdateDate): NetworkResult<Unit> {
        return handleApi { codiService.updateDate(info) }
    }

}