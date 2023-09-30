package com.dadada.onecloset.data.model.closet.response

import com.dadada.onecloset.domain.model.clothes.ClothesInfo

data class ClothResponse (
    val code: Int,
    val data: ClothesInfo,
    val message: String
)