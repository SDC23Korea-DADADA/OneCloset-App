package com.dadada.onecloset.data.model.closet.response

import com.dadada.onecloset.domain.model.ClothAnalysis

data class ClothAnalysisResponse(
    val code: Int,
    val data: ClothAnalysis,
    val message: String
)