package com.dadada.onecloset.presentation.ui.utils

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.dadada.onecloset.presentation.R

fun iconHandler(iconId: Int): Int {
    return when (iconId) {
        1 -> R.drawable.ic_shirt
        else -> iconId
    }
}

fun hexStringToColor(hex: String): Color {
    return Color(android.graphics.Color.parseColor(hex))
}

fun colorToHexString(color: Color): String {
    return "#${Integer.toHexString(color.toArgb())}"
}
