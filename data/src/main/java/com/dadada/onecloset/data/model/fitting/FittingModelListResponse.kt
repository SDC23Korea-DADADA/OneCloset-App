package com.dadada.onecloset.data.model.fitting

import com.dadada.onecloset.domain.model.fitting.FittingModelInfo

data class FittingModelListResponse(
    val code: Int,
    val data: List<FittingModelInfo>,
    val message: String
)