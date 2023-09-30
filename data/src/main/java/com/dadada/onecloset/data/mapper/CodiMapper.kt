package com.dadada.onecloset.data.mapper

import com.dadada.onecloset.data.model.codi.response.CodiListResponse
import com.dadada.onecloset.data.model.codi.response.CodiRegisterResponse
import com.dadada.onecloset.domain.model.codi.CodiList

fun CodiRegisterResponse.toDomain() : Long {
    return data
}

fun CodiListResponse.toDomain() : CodiList {
    return data
}