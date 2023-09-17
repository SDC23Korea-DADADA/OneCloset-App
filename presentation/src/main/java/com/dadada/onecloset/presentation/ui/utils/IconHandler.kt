package com.dadada.onecloset.presentation.ui.utils

import androidx.compose.ui.graphics.Color
import com.dadada.onecloset.presentation.R

fun iconHandler(iconId: Int): Int {
    return when (iconId) {
        1 -> R.drawable.ic_shirt
        else -> R.drawable.ic_bow_tie
    }
}

fun hexStringToColor(hex: String): Color {
    return Color(android.graphics.Color.parseColor(hex))
}