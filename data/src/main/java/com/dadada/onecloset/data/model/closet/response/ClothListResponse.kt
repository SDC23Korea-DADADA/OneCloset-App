package com.dadada.onecloset.data.model.closet.response

import com.dadada.onecloset.domain.model.Cloth

data class ClothListResponse(
    val code: Int,
    val data: List<Cloth>,
    val message: String
)