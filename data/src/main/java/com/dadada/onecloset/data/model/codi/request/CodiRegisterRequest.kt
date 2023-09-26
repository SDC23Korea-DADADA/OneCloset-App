package com.dadada.onecloset.data.model.codi.request

data class CodiRegisterRequest (
    var date: String = "",
    var clothesList: List<Int> = listOf(),
)