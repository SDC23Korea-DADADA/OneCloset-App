package com.dadada.onecloset.data.mapper

import com.dadada.onecloset.data.model.account.response.AccountResponse
import com.dadada.onecloset.data.model.account.response.LogInResponse
import com.dadada.onecloset.domain.model.AccountInfo
import com.dadada.onecloset.domain.model.Token

fun LogInResponse.toDomain(): Token {
    return data
}

fun AccountResponse.toDomain(): AccountInfo {
    return data
}
