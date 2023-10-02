package com.dadada.onecloset.presentation.ui.clothes.component.row

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.dadada.onecloset.presentation.ui.theme.Green
import com.dadada.onecloset.presentation.ui.theme.Paddings
import com.dadada.onecloset.presentation.ui.theme.Typography

@Composable
fun ClothesInfoRow(title: String, content: String) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = Paddings.xlarge, vertical = Paddings.small),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            modifier = Modifier.padding(start = Paddings.medium),
            text = title,
            style = Typography.titleSmall,
        )
        SuggestionChip(
            border = SuggestionChipDefaults.suggestionChipBorder(borderColor = Green),
            onClick = { },
            label = { Text(text = content, color = Color.White) },
            colors = SuggestionChipDefaults.suggestionChipColors(containerColor = Green),
        )
    }
}
