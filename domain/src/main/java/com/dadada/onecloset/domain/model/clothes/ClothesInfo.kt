package com.dadada.onecloset.domain.model.clothes

data class ClothesInfo(
    var airDressor: String = "",
    val careTipList: List<Any> = listOf(),
    val clothesId: Int = -1,
    var color: String = "",
    var colorCode: String = "",
    var description: String = "1",
    var dryer: String = "",
    var hashtagList: List<String> = listOf(),
    var image: String = "",
    var laundry: String = "",
    val laundryTipList: List<Any> = listOf(),
    var material: String = "",
    var tpoList: List<String> = listOf(),
    var type: String = "",
    var weatherList: List<String> = listOf(),
    val thumnailUrl: String = "",
    var closetId: String = "",
    var upperType: String = ""
) {
    fun isEmptyAdditionalInfo() : Boolean {
        return hashtagList.isEmpty() && tpoList.isEmpty() && weatherList.isEmpty()
    }
}