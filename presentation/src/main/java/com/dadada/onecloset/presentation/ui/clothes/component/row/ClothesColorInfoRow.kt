package com.dadada.onecloset.presentation.ui.clothes.component.row

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.dadada.onecloset.presentation.ui.clothes.component.item.ColorItem
import com.dadada.onecloset.presentation.ui.clothes.component.item.ColorMultiItem
import com.dadada.onecloset.presentation.ui.theme.screenModifier
import com.dadada.onecloset.presentation.ui.theme.Paddings
import com.dadada.onecloset.presentation.ui.theme.Typography

@Composable
fun ClothesInfoRow(title: String, content: Color, colorName: String) {
    Row(
        modifier = screenModifier.padding(vertical = Paddings.small),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            modifier = Modifier.padding(start = Paddings.medium),
            text = title,
            style = Typography.titleSmall,
        )
        if (content == Color.Magenta) {
            ColorMultiItem()
        } else {
            ColorItem(color = content, name = colorName)
        }
    }
}
