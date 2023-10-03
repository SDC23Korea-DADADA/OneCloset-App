package com.dadada.onecloset.presentation.ui.closet.component.item

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.dadada.onecloset.presentation.ui.theme.roundedSquareSmallModifier

@Composable
fun ClosetIconItem(
    modifier: Modifier = roundedSquareSmallModifier,
    icon: Int,
    backGroundTint: Color,
) {
    Box(
        modifier = modifier
            .size(48.dp)
            .background(backGroundTint),
    ) {
        Icon(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(4.dp),
            painter = painterResource(id = icon),
            contentDescription = "아이콘",
            tint = Color.White,
        )
    }
}
