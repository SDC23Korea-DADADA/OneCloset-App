package com.dadada.onecloset.domain.model.codi

data class Fitting(
    val clothesList: List<Clothes>,
    val fittingImg: String,
    val fittingThumbnailImg: String,
    val id: Long,
    val originImg: String,
    val wearingAtDay: String?,
    val wearingAtMonth: String?
)