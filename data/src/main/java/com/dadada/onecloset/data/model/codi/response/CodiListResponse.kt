package com.dadada.onecloset.data.model.codi.response

import com.dadada.onecloset.domain.model.codi.CodiList

data class CodiListResponse(
    val code: Int,
    val data: CodiList,
    val message: String
)