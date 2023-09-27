package com.dadada.onecloset.domain.model.codi

data class Codi(
    val clothesList: List<Clothes> = listOf(),
    var id: Long = 0L,
    var originImg: String = "",
    val thumbnailImg: String = "",
    val wearingAtDay: String? = null,
    val wearingAtMonth: String? = null
)