package com.dadada.onecloset.presentation.ui.utils

import androidx.compose.ui.graphics.Color
import com.dadada.onecloset.presentation.R
import com.dadada.onecloset.presentation.ui.theme.Blue
import com.dadada.onecloset.presentation.ui.theme.BluePurple
import com.dadada.onecloset.presentation.ui.theme.Gray
import com.dadada.onecloset.presentation.ui.theme.Green
import com.dadada.onecloset.presentation.ui.theme.Navy
import com.dadada.onecloset.presentation.ui.theme.Orange
import com.dadada.onecloset.presentation.ui.theme.Pink
import com.dadada.onecloset.presentation.ui.theme.Purple
import com.dadada.onecloset.presentation.ui.theme.SkyBlue

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
    Color1(Blue),
    Color2(Green),
    Color3(Orange),
    Color4(Gray),
    Color5(SkyBlue),
    Color6(Purple),
    Color7(BluePurple),
    Color8(Navy),
    Color9(Pink)
}
