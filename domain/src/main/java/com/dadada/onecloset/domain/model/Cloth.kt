package com.dadada.onecloset.domain.model

data class Cloth(
    var airDressor: String = "",
    val careTipList: List<Any> = listOf(),
    val clothesId: Int = -1,
    val color: String = "",
    var colorCode: String = "",
    val description: String = "1",
    var dryer: String = "",
    val hashtagList: List<String> = listOf(),
    var img: String = "",
    var laundry: String = "",
    val laundryTipList: List<Any> = listOf(),
    var material: String = "",
    val tpoList: List<String> = listOf(),
    var type: String = "",
    val weatherList: List<String> = listOf(),
    val thumnailUrl: String = "",
    var closetId: String = "",
    var upperType: String = ""
)