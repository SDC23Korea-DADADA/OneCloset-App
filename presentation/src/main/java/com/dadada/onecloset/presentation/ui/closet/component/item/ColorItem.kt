package com.dadada.onecloset.presentation.ui.closet.component.item

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ColorItem(color: Color, selectedColor: MutableState<Color>) {
    Box(
        modifier = Modifier
            .padding(4.dp)
            .clip(CircleShape)
            .size(32.dp)
            .background(color)
            .clickable { selectedColor.value = color },
    )
}