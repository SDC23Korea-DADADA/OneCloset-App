package com.dadada.onecloset.domain.model.codi

data class CodiRegisterInfo (
    val date: String = "",
    val clothesList: List<Int> = listOf(),
    val codiid: Long = 0L,
)
