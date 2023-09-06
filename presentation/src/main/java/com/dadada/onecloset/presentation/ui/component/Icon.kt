package com.dadada.onecloset.presentation.ui.component

import android.graphics.Paint.Align
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun ClosetIcon(modifier: Modifier, icon: Int, color: Color) {
    Box(
        modifier = modifier
            .size(48.dp)
            .padding(4.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(color)
    ) {
        Icon(
            modifier = Modifier.align(Alignment.Center).padding(4.dp),
            painter = painterResource(id = icon),
            contentDescription = "아이콘",
            tint = Color.White
        )
    }
}