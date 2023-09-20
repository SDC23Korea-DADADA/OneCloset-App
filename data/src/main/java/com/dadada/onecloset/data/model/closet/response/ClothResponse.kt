package com.dadada.onecloset.data.model.closet.response

import com.dadada.onecloset.domain.model.Cloth

data class ClothResponse (
    val code: Int,
    val data: Cloth,
    val message: String
)