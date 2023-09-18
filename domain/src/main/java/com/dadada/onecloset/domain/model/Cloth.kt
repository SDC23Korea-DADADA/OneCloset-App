package com.dadada.onecloset.domain.model

data class Cloth(
    val clothesId: String = "",
    val color: String = "",
    val description: String = "",
    val hashTag: List<String> = listOf(),
    val material: String = "",
    val thumnailImg: String = "https://image.musinsa.com/images/prd_img/2022062010181200000020384.jpg",
    val tpo: List<String> = listOf(),
    val type: String = "",
    val url: String = "",
    val weather: List<String> = listOf()
)