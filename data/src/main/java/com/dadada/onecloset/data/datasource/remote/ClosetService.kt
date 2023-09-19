package com.dadada.onecloset.data.datasource.remote

import com.dadada.onecloset.data.model.ServerResponse
import com.dadada.onecloset.data.model.closet.response.ClosetListResponse
import com.dadada.onecloset.data.model.closet.response.ClothListResponse
import com.dadada.onecloset.data.model.closet.response.ClothResponse
import com.dadada.onecloset.domain.model.Closet
import okhttp3.MultipartBody
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ClosetService {

    @GET("api/closet/list")
    suspend fun getClosetList() : ClosetListResponse

    @POST("api/closet")
    suspend fun putCloset(@Body closet: Closet) : ServerResponse

    @PUT("api/closet")
    suspend fun updateCloset(@Body closet: Closet) : ServerResponse

    @DELETE("api/closet/{id}")
    suspend fun deleteCloset(@Path(value = "id") id: String) : ServerResponse

    @GET("api/clothes/list/{id}")
    suspend fun getClothList(@Path(value = "id") id: String) : ClothListResponse

    @Multipart
    @POST("api/clothes/")
    suspend fun putCloth(@Body cloth: MultipartBody) : ServerResponse

    @GET("api/clothes/{id}")
    suspend fun getCloth(@Path(value = "id") id: String) : ClothResponse

}