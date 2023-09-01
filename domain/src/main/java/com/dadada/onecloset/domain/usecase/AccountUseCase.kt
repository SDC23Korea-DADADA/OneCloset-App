package com.dadada.onecloset.domain.usecase

import com.dadada.onecloset.domain.model.AccountInfo
import com.dadada.onecloset.domain.repository.AccountRepository
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class AccountUseCase @Inject constructor(
    private val accountRepository: AccountRepository
) {

    fun getAccountInfo() : StateFlow<AccountInfo?> {
        return accountRepository.getAccountInfo()
    }

    suspend fun signIn(accountInfo: AccountInfo) {
        accountRepository.signInGoogle(accountInfo)
    }

    suspend fun  signOut() {
        accountRepository.signOutGoogle()
    }
}