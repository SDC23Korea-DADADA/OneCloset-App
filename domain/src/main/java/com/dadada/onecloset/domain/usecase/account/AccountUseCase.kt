package com.dadada.onecloset.domain.usecase.account

import com.dadada.onecloset.domain.model.AccountInfo
import com.dadada.onecloset.domain.model.NetworkResult
import com.dadada.onecloset.domain.model.Token
import com.dadada.onecloset.domain.repository.AccountRepository
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class AccountUseCase @Inject constructor(
    private val accountRepository: AccountRepository
) {

    fun getAccountInfo() : StateFlow<AccountInfo?> {
        return accountRepository.getAccountInfo()
    }

    suspend fun getAccountInfoFromRemote() : NetworkResult<AccountInfo> {
        return accountRepository.getAccountInfoFromRemote()
    }

    suspend fun signIn(accountInfo: AccountInfo) {
        accountRepository.signIn(accountInfo)
    }


    suspend fun  signOut() {
        accountRepository.signOutGoogle()
    }
}