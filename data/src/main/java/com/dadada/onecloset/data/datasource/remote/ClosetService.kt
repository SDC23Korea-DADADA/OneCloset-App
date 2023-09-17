package com.dadada.onecloset.data.datasource.remote

import com.dadada.onecloset.data.model.closet.response.ClosetListResponse
import com.dadada.onecloset.domain.model.Closet
import retrofit2.http.GET

interface ClosetService {

    @GET("api/closet/list")
    suspend fun getClosetList() : ClosetListResponse
}