package com.dadada.onecloset.presentation.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.dadada.onecloset.presentation.ui.theme.PrimaryBlack
import com.dadada.onecloset.presentation.ui.theme.Typography

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
                    label = { Text(text = "반팔", color = Color.White) },
                    colors = SuggestionChipDefaults.suggestionChipColors( containerColor = PrimaryBlack)
                )
            },
            reverse = reverse.value,
            onClick = { reverse.value = !reverse.value }
        )
    }
}


