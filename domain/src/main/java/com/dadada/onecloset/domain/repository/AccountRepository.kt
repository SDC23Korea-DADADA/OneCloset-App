package com.dadada.onecloset.domain.repository

import com.dadada.onecloset.domain.model.AccountInfo
import com.dadada.onecloset.domain.model.NetworkResult
import com.dadada.onecloset.domain.model.Token
import kotlinx.coroutines.flow.StateFlow

interface AccountRepository {
    fun getAccountInfo(): StateFlow<AccountInfo?>

    suspend fun getAccountInfoFromRemote(): NetworkResult<AccountInfo>
    suspend fun signIn(accountInfo: AccountInfo)

    suspend fun signOut()

    suspend fun logInKakao(token: String): NetworkResult<Token>
    suspend fun leaveUser() : NetworkResult<Unit>
}