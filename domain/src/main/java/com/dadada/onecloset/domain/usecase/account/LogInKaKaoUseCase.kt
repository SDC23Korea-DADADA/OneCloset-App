package com.dadada.onecloset.domain.usecase.account

import android.util.Log
import com.dadada.onecloset.domain.model.NetworkResult
import com.dadada.onecloset.domain.model.Token
import com.dadada.onecloset.domain.repository.AccountRepository
import javax.inject.Inject

class LogInKaKaoUseCase @Inject constructor (
    private val accountRepository: AccountRepository
) {

    suspend operator fun invoke(token: String): NetworkResult<Token> {
        return accountRepository.logInKakao(token)
    }
}