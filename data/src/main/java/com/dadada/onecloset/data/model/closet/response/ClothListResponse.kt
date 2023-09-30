package com.dadada.onecloset.data.model.closet.response

import com.dadada.onecloset.domain.model.clothes.ClothesInfo

data class ClothListResponse(
    val code: Int,
    val data: List<ClothesInfo>,
    val message: String
)