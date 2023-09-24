package com.dadada.onecloset.data.model.fitting.response

import com.dadada.onecloset.domain.model.fitting.FittingResult

data class FittingResultResponse(
    val code: Int,
    val data: FittingResult,
    val message: String
)