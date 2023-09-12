package com.dadada.onecloset.presentation.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

val roundedShapeLargeModifier = Modifier
    .fillMaxWidth()
    .clip(RoundedCornerShape(26.dp))
    .background(Color.White)
    .padding(16.dp)

val roundedShapeMediumModifier = Modifier
    .fillMaxWidth()
    .clip(RoundedCornerShape(20.dp))
    .background(Color.White)
    .padding(16.dp)

val roundedShapeSmallModifier = Modifier
    .padding(4.dp)
    .clip(RoundedCornerShape(14.dp))