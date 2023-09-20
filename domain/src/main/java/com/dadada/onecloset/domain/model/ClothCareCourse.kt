package com.dadada.onecloset.domain.model

data class ClothCareCourse(
    val airDresser: String,
    val careTip: List<String>,
    val dryer: String,
    val laundry: String,
    val laundryTip: List<String>
)