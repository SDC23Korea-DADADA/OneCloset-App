package com.dadada.onecloset.data.model.closet.response

import com.dadada.onecloset.domain.model.ClothCareCourse

data class ClothCareCourseResponse(
    val code: Int,
    val data: ClothCareCourse,
    val message: String
)