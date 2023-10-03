package com.dadada.onecloset.presentation.ui.clothes.component.row

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.dadada.onecloset.presentation.ui.clothes.component.item.ColorItem
import com.dadada.onecloset.presentation.ui.clothes.component.item.ColorMultiItem
import com.dadada.onecloset.presentation.ui.common.row.DropDownRow
import com.dadada.onecloset.presentation.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClothesColorEditRow(
    title: String,
    content: Color,
    name: String = "",
    sheetState: SheetState,
    onClick: () -> Unit,
) {
    val reverse = sheetState.isVisible
    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            modifier = Modifier.padding(start = 8.dp),
            text = title,
            style = Typography.titleSmall,
        )
        DropDownRow(
            component = {
                if (name == "다채색") {
                    ColorMultiItem()
                } else {
                    ColorItem(color = content, name = name)
                }
            },
            reverse = reverse,
            onClick = { onClick() },
        )
    }
}
