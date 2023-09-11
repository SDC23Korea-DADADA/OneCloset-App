package com.dadada.onecloset.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.dadada.onecloset.presentation.ui.theme.PrimaryBlack
import com.dadada.onecloset.presentation.ui.theme.Typography
import com.dadada.onecloset.presentation.ui.utils.ClothColor

@Composable
fun DropDownRow(component: @Composable () -> Unit, reverse: Boolean, onClick: () -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        component()
        DropDownButton(reverse = reverse) {
            onClick()
        }
    }
}

@Composable
fun ChipEditRow(title: String, content: String, reverse: MutableState<Boolean>) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            modifier = Modifier.padding(start = 8.dp),
            text = title,
            style = Typography.titleMedium
        )
        DropDownRow(
            component = {
                SuggestionChip(
                    onClick = { /*TODO*/ },
                    label = { Text(text = content, color = Color.White) },
                    colors = SuggestionChipDefaults.suggestionChipColors(containerColor = PrimaryBlack)
                )
            },
            reverse = reverse.value,
            onClick = { reverse.value = !reverse.value }
        )
    }
}

@Composable
fun ColorEditRow(title: String, content: Color, reverse: MutableState<Boolean>) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            modifier = Modifier.padding(start = 8.dp),
            text = title,
            style = Typography.titleMedium
        )
        DropDownRow(
            component = {
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(36.dp)
                        .background(content)
                        .border(1.dp, Color.Black, CircleShape)
                )
            },
            reverse = reverse.value,
            onClick = { reverse.value = !reverse.value }
        )
    }
}

@Composable
fun RowWithTwoButtons(
    left: String,
    right: String,
    onClickLeft: () -> Unit,
    onClickRight: () -> Unit
) {
    Row(modifier = Modifier.fillMaxWidth().padding()) {
        Text(modifier = Modifier
            .clickable { onClickLeft() }
            .weight(1f).padding(vertical = 12.dp), text = left, style = Typography.titleLarge, textAlign = TextAlign.Center)
        Text(modifier = Modifier
            .clickable { onClickRight() }
            .weight(1f).padding(vertical = 12.dp), text = right, style = Typography.titleLarge, textAlign = TextAlign.Center)
    }
}

