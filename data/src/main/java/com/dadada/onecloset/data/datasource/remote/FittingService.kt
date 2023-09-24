package com.dadada.onecloset.data.datasource.remote

import com.dadada.onecloset.data.model.ServerResponse
import com.dadada.onecloset.data.model.fitting.response.FittingModelListResponse
import com.dadada.onecloset.data.model.fitting.response.FittingResultResponse
import com.dadada.onecloset.domain.model.fitting.FittingInfo
import com.dadada.onecloset.domain.model.fitting.FittingResultForSave
import okhttp3.MultipartBody
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface FittingService {

    @Multipart
    @POST("api/fitting/model")
    suspend fun putModel(@Part image: MultipartBody.Part): ServerResponse

    @POST("api/fitting")
    suspend fun getVirtualFittingResult(@Body fittingInform: FittingInfo): FittingResultResponse

    @GET("api/fitting/model/list")
    suspend fun getModelList(): FittingModelListResponse

    @DELETE("api/fitting/model/{id}")
    suspend fun deleteModel(@Path(value = "id") id: String): ServerResponse

    @POST("api/fitting/save")
    suspend fun putFittingResult(@Body fittingResult: FittingResultForSave): ServerResponse

    @POST("api/fitting/save/{date}")
    suspend fun putFittingResultWithDate(
        @Path(value = "date") date: String,
        @Body fittingResult: FittingResultForSave
    ): ServerResponse
}