package com.dadada.onecloset.domain.model

data class AccountInfo(
    val accessToken: String = "",
    val refreshToken: String = "",
    val profileImage: String = "",
    val gender: String = "",
    val nickName: String = "",
    val email: String = "",
    val social: String = ""
)
