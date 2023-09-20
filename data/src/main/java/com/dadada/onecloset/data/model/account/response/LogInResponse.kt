package com.dadada.onecloset.data.model.account.response

import com.dadada.onecloset.domain.model.Token

data class LogInResponse(
    val code: Int,
    val data: Token,
    val message: String
)