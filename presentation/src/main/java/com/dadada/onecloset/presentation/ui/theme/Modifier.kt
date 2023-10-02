package com.dadada.onecloset.presentation.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

val screenModifier = Modifier
    .fillMaxSize()
    .padding(horizontal = Paddings.xlarge)

val roundedSquareLargeModifier = Modifier
    .fillMaxWidth()
    .clip(RoundedCornerShape(26.dp))
    .background(Color.White)

val roundedSquareMediumModifier = Modifier
    .padding(Paddings.small)
    .clip(RoundedCornerShape(20.dp))
    .background(Color.White)

val roundedSquareSmallModifier = Modifier
    .padding(Paddings.small)
    .clip(RoundedCornerShape(14.dp))

val circleShapeModifier = Modifier
    .padding(Paddings.small)
    .clip(CircleShape)
    .background(Color.White)
