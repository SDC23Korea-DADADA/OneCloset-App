package com.dadada.onecloset.domain.model.codi

data class Fitting(
    val clothesList: List<Clothes> = listOf(),
    val fittingImg: String = "",
    val fittingThumbnailImg: String = "",
    val id: Long = 0L,
    val originImg: String = "",
    val wearingAtDay: String? = null,
    val wearingAtMonth: String? = null
)