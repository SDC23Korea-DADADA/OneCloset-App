package com.dadada.onecloset.data.repository

import android.content.Context
import android.util.Log
import com.dadada.onecloset.data.datasource.remote.CodiService
import com.dadada.onecloset.data.datasource.remote.handleApi
import com.dadada.onecloset.data.mapper.Converter
import com.dadada.onecloset.data.mapper.toDomain
import com.dadada.onecloset.data.model.codi.request.CodiRegisterRequest
import com.dadada.onecloset.domain.model.NetworkResult
import com.dadada.onecloset.domain.model.codi.CodiList
import com.dadada.onecloset.domain.model.codi.CodiRegisterInfo
import com.dadada.onecloset.domain.model.codi.CodiUpdateDate
import com.dadada.onecloset.domain.repository.CodiRepository
import com.google.gson.Gson
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import javax.inject.Inject

private const val TAG = "CodiRepositoryImpl"
class CodiRepositoryImpl @Inject constructor(
    private val codiService: CodiService,
    private val context: Context
) : CodiRepository {
    override suspend fun putCodi(info: CodiRegisterInfo): NetworkResult<Long> {
        val request = CodiRegisterRequest(info.date, info.clothesList)
        val requestDto = RequestBody.create("application/json".toMediaTypeOrNull(), Gson().toJson(request))
        Log.d(TAG, "putCodi: $request")
        return handleApi { codiService.putCodi(Converter.createMultipartBodyPart(context, info.imagePath), requestDto).toDomain()}
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

    override suspend fun getCodiList(): NetworkResult<CodiList> {
        return handleApi { codiService.getCodiList().toDomain() }
    }

    override suspend fun getCodiListByMonth(date: String): NetworkResult<CodiList> {
        return handleApi { codiService.getCodiListByMonth(date).data }
    }

}