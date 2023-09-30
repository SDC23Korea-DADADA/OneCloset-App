package com.dadada.onecloset.data.datasource.remote

import com.dadada.onecloset.data.model.ServerResponse
import com.dadada.onecloset.data.model.codi.response.CodiListResponse
import com.dadada.onecloset.data.model.codi.response.CodiRegisterResponse
import com.dadada.onecloset.domain.model.codi.CodiUpdateDate
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path

interface CodiService {
    @Multipart
    @POST("api/codi")
    suspend fun putCodi(
        @Part image: MultipartBody.Part,
        @Part("info") info:  RequestBody,
    ) : CodiRegisterResponse

    @Multipart
    @PUT("api/codi")
    suspend fun updateCodi(
        @Part image: MultipartBody.Part,
        @Part("info") info:  RequestBody,
    ) : ServerResponse

    @Multipart
    @PUT("api/codi/date")
    suspend fun updateDate(@Body codiUpdateDate: CodiUpdateDate) : ServerResponse

    @DELETE("api/codi/{id}")
    suspend fun deleteCodi(@Path(value = "id") id: String) : ServerResponse

    @GET("api/fitting/list")
    suspend fun getCodiList() : CodiListResponse

    @GET("api/fitting/list/month/{date}")
    suspend fun getCodiListByMonth(@Path(value = "date") date: String) : CodiListResponse

}