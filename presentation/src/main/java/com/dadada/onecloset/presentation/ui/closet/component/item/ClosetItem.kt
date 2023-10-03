package com.dadada.onecloset.presentation.ui.closet.component.item

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.dadada.onecloset.presentation.ui.theme.Size
import com.dadada.onecloset.presentation.ui.theme.Typography

@Composable
fun ClosetItem(
    modifier: Modifier = Modifier,
    title: String,
    icon: Int,
    backGroundTint: Color,
    onClick: () -> Unit,
) {
    Column(
        modifier = modifier.clickable(onClick = onClick),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ClosetIconItem(icon = icon, backGroundTint = backGroundTint)
        Spacer(modifier = Modifier.size(Size.xsmall))
        Text(
            text = title,
            style = Typography.titleSmall,
            textAlign = TextAlign.Center,
        )
    }
}
