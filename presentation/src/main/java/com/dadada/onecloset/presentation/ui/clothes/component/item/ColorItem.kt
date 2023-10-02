package com.dadada.onecloset.presentation.ui.clothes.component.item

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.dadada.onecloset.presentation.ui.theme.Paddings
import com.dadada.onecloset.presentation.ui.theme.Typography

@Composable
fun ColorItem(modifier: Modifier = Modifier, color: Color, name: String) {
    Column(
        modifier = modifier.padding(Paddings.small),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .size(32.dp)
                .background(color = color)
                .border(1.dp, Color.Black, CircleShape),
        )
        Text(text = name, style = Typography.bodySmall.copy(color = Color.Black))
    }
}
