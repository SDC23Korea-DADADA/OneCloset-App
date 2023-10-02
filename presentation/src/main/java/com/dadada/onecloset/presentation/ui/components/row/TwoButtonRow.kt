package com.dadada.onecloset.presentation.ui.components.row

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.dadada.onecloset.presentation.ui.theme.Typography

@Composable
fun TwoButtonRow(
    modifier: Modifier = Modifier,
    left: String,
    right: String,
    onClickLeft: () -> Unit,
    onClickRight: () -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(),
    ) {
        Text(
            modifier = Modifier
                .clickable { onClickLeft() }
                .weight(1f)
                .padding(vertical = 12.dp),
            text = left,
            style = Typography.titleLarge.copy(fontWeight = FontWeight.ExtraBold),
            textAlign = TextAlign.Center,
        )
        Text(
            modifier = Modifier
                .clickable { onClickRight() }
                .weight(1f)
                .padding(vertical = 12.dp),
            text = right,
            style = Typography.titleLarge.copy(fontWeight = FontWeight.ExtraBold),
            textAlign = TextAlign.Center,
        )
    }
}
