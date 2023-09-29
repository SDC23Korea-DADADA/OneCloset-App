package com.dadada.onecloset.data.datasource.remote

import com.dadada.onecloset.data.model.ServerResponse
import com.dadada.onecloset.data.model.account.response.AccountResponse
import com.dadada.onecloset.data.model.account.response.LogInResponse
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AccountService {
    @POST("api/login/kakao/access/{access}")
    suspend fun logInKakao(@Path(value = "access") token: String): LogInResponse

    @GET("api/user")
    suspend fun getAccountInfoFromRemote(): AccountResponse

    @POST("api/user/leave")
    suspend fun leaveUser() : ServerResponse

}