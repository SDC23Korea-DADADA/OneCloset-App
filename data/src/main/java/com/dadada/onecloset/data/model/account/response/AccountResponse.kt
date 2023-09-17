package com.dadada.onecloset.data.model.account.response

data class AccountResponse(
    val code: Int,
    val data: AccountInfo,
    val message: String
)