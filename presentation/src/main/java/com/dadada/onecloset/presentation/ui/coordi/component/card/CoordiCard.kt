package com.dadada.onecloset.presentation.ui.coordi.component.card

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.dadada.onecloset.presentation.ui.theme.Gray
import com.dadada.onecloset.presentation.ui.theme.Paddings
import com.dadada.onecloset.presentation.ui.theme.Size
import com.dadada.onecloset.presentation.ui.theme.Typography
import com.dadada.onecloset.presentation.ui.theme.roundedSquareLargeModifier

@Composable
fun CodiCard(
    title: String,
    content: String,
    onClick: () -> Unit,
    iconView: @Composable () -> Unit,
) {
    Row(
        modifier = roundedSquareLargeModifier
            .clickable(onClick = onClick)
            .padding(Paddings.xlarge),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        iconView()
        Spacer(modifier = Modifier.size(Size.large))
        Column {
            Text(text = title, style = Typography.titleMedium)
            Spacer(modifier = Modifier.size(Size.small))
            Text(text = content, style = Typography.bodySmall.copy(color = Gray))
        }
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            modifier = Modifier.size(Size.extra),
            imageVector = Icons.Outlined.KeyboardArrowRight,
            tint = Gray,
            contentDescription = "화살표 아이콘",
        )
    }
}
