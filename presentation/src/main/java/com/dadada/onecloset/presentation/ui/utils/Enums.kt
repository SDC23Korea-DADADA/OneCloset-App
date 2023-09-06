package com.dadada.onecloset.presentation.ui.utils

import androidx.compose.ui.graphics.Color
import com.dadada.onecloset.presentation.R
import com.dadada.onecloset.presentation.ui.theme.iconBlue
import com.dadada.onecloset.presentation.ui.theme.iconBluePurple
import com.dadada.onecloset.presentation.ui.theme.iconGray
import com.dadada.onecloset.presentation.ui.theme.iconGreen
import com.dadada.onecloset.presentation.ui.theme.iconNavy
import com.dadada.onecloset.presentation.ui.theme.iconOrange
import com.dadada.onecloset.presentation.ui.theme.iconPink
import com.dadada.onecloset.presentation.ui.theme.iconPurple
import com.dadada.onecloset.presentation.ui.theme.iconSkyBlue

enum class IconEnum (val resId: Int) {
    ICON1(R.drawable.ic_bow_tie),
    ICON2(R.drawable.ic_cap),
    ICON3(R.drawable.ic_cardigan),
    ICON4(R.drawable.ic_coat),
    ICON5(R.drawable.ic_down_jacket),
    ICON6(R.drawable.ic_jacket),
    ICON7(R.drawable.ic_jeans),
    ICON8(R.drawable.ic_raincoat),
    ICON9(R.drawable.ic_shirt),
    ICON10(R.drawable.ic_socks),
    ICON11(R.drawable.ic_sweat),
    ICON12(R.drawable.ic_t_shirt),
    ICON13(R.drawable.ic_tie),
}

enum class ColorEnum(val color: Color) {
    Color1(iconBlue),
    Color2(iconGreen),
    Color3(iconOrange),
    Color4(iconGray),
    Color5(iconSkyBlue),
    Color6(iconPurple),
    Color7(iconBluePurple),
    Color8(iconNavy),
    Color9(iconPink)
}