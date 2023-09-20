package com.dadada.onecloset.data.model.account.response

import com.dadada.onecloset.domain.model.AccountInfo

data class AccountResponse(
    val code: Int,
    val data: AccountInfo,
    val message: String
)