package com.dadada.onecloset.domain.model.codi

data class Codi(
    val clothesList: List<Clothes>,
    val id: Long,
    val originImg: String,
    val thumbnailImg: String,
    val wearingAtDay: String?,
    val wearingAtMonth: String?
)