package com.dadada.onecloset.data.mapper

import com.dadada.onecloset.data.model.fitting.response.FittingModelListResponse
import com.dadada.onecloset.data.model.fitting.response.FittingResultResponse
import com.dadada.onecloset.domain.model.fitting.FittingModelInfo
import com.dadada.onecloset.domain.model.fitting.FittingResult

fun FittingModelListResponse.toDomain() : List<FittingModelInfo> {
    return data
}

fun FittingResultResponse.toDomain() : FittingResult {
    return data
}