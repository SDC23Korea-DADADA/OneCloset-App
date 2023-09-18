package com.dadada.onecloset.domain.model

import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class Cloth(
    val clothesId: String = "",
    val color: String = "",
    val description: String = "",
    val hashTag: List<String>,
    val material: String = "",
    val thumnailUrl: String = "",
    val tpo: List<String> = listOf(),
    val type: String = "",
    val url: String = "",
    val weather: List<String> = listOf()
)