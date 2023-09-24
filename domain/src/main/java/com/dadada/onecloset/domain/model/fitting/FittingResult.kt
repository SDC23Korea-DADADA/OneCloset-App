package com.dadada.onecloset.domain.model.fitting

data class FittingResult(
    val clothesInfoList: List<FittingClothesInfo> = listOf(),
    val fittingImg: String = "",
    val modelId: Int = 0,
    val originImg: String = ""
)