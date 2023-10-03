package com.dadada.onecloset.presentation.ui.clothes.component.row

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.dadada.onecloset.presentation.ui.common.row.DropDownRow
import com.dadada.onecloset.presentation.ui.theme.Green
import com.dadada.onecloset.presentation.ui.theme.Paddings
import com.dadada.onecloset.presentation.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClothesInfoEditRow(
    title: String,
    content: String,
    sheetState: SheetState,
    onClick: () -> Unit,
) {
    val reverse = sheetState.isVisible
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
        DropDownRow(
            component = {
                AssistChip(
                    onClick = { },
                    label = { Text(text = content, color = Color.White) },
                    colors = AssistChipDefaults.assistChipColors(containerColor = Green),
                    border = AssistChipDefaults.assistChipBorder(borderColor = Green),
                )
            },
            reverse = reverse,
            onClick = { onClick() },
        )
    }
}
