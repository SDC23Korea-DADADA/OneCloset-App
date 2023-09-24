package com.dadada.onecloset.data.mapper

import com.dadada.onecloset.data.model.codi.CodiRegisterResponse

fun CodiRegisterResponse.toDomain() : Long {
    return data
}