package com.dadada.onecloset.data.repository

import android.util.Log
import com.dadada.onecloset.data.datasource.local.PreferenceDataSource
import com.dadada.onecloset.data.datasource.remote.AccountService
import com.dadada.onecloset.data.datasource.remote.handleApi
import com.dadada.onecloset.data.mapper.toDomain
import com.dadada.onecloset.domain.model.AccountInfo
import com.dadada.onecloset.domain.model.NetworkResult
import com.dadada.onecloset.domain.model.Token
import com.dadada.onecloset.domain.repository.AccountRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
    private val preferenceDataSource: PreferenceDataSource,
    private val accountService: AccountService
) : AccountRepository {
    private val accountInfoFlow = MutableStateFlow(preferenceDataSource.getAccountInfo())

    override fun getAccountInfo(): StateFlow<AccountInfo?> {
        return accountInfoFlow
    }

    override suspend fun getAccountInfoFromRemote(): NetworkResult<AccountInfo> {
        return handleApi { accountService.getAccountInfoFromRemote().toDomain() }
    }

    override suspend fun signIn(accountInfo: AccountInfo) {
        preferenceDataSource.putAccountInfo(accountInfo)
        accountInfoFlow.emit(accountInfo)
    }

    override suspend fun signOut() {
        preferenceDataSource.removeAccountInfo()
        accountInfoFlow.emit(null)
    }

    private val TAG = "AccountRepositoryImpl"
    override suspend fun logInKakao(token: String): NetworkResult<Token> {
        Log.d(TAG, "logInKakao: $token")
        return handleApi { accountService.logInKakao(token = token).toDomain() }
    }
}