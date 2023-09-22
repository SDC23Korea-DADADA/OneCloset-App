package com.dadada.onecloset.domain.model.fitting

data class FittingResultForSave(
    var clothesIdList: List<Int> = listOf(),
    var fittingImg: String = "",
    var modelId: String = ""
)