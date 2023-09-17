package com.dadada.onecloset.data.model.account.response

data class LogInResponse(
    val code: Int,
    val data: Token,
    val message: String
)