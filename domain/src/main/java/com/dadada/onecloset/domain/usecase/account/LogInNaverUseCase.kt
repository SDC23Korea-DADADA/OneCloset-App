package com.dadada.onecloset.domain.usecase.account

import com.dadada.onecloset.domain.model.NetworkResult
import com.dadada.onecloset.domain.model.Token
import com.dadada.onecloset.domain.repository.AccountRepository
import javax.inject.Inject

class LogInNaverUseCase @Inject constructor (
    private val accountRepository: AccountRepository
) {

    suspend operator fun invoke(token: String): NetworkResult<Token> {
        return accountRepository.logInNaver(token)
    }
}