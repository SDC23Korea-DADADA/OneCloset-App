package com.dadada.onecloset.data.mapper

import com.dadada.onecloset.data.model.account.response.AccountResponse
import com.dadada.onecloset.data.model.account.response.LogInResponse
import com.dadada.onecloset.domain.model.AccountInfo
import com.dadada.onecloset.domain.model.Token

fun LogInResponse.toDomain(): Token{
    return Token(
        accessToken = data.accessToken,
        refreshToken = data.refreshToken
    )
}

fun AccountResponse.toDomain() : AccountInfo {
    return AccountInfo(
        profileImage = data.profileImg,
        gender = data.gender,
        nickName = data.nickname,
        email = data.email,
        type = data.social
    )
}