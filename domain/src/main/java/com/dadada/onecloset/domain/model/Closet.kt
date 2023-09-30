package com.dadada.onecloset.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Closet(
    val closetId: Int = 0,
    val colorCode: String = "",
    val icon: Int = 0,
    val name: String = ""
) : Parcelable