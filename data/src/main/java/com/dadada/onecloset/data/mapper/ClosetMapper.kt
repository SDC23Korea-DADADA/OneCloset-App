package com.dadada.onecloset.data.mapper

import com.dadada.onecloset.data.model.closet.response.ClosetListResponse
import com.dadada.onecloset.domain.model.Closet

fun ClosetListResponse.toDomain(): List<Closet> {
    return data
}
