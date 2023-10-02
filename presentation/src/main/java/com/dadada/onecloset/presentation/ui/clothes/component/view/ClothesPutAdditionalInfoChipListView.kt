package com.dadada.onecloset.presentation.ui.clothes.component.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.dadada.onecloset.presentation.ui.theme.Paddings
import com.dadada.onecloset.presentation.ui.theme.PrimaryBlack
import com.dadada.onecloset.presentation.ui.theme.Typography

@Composable
fun ClothesPutAdditionalInfoChipListView(
    title: String,
    contentList: List<String>,
    chipState: MutableState<MutableList<Boolean>>,
) {
    Column {
        Text(
            text = title,
            style = Typography.titleMedium.copy(fontWeight = FontWeight.ExtraBold),
        )
        LazyVerticalGrid(columns = GridCells.Fixed(5)) {
            items(contentList.size) {
                SuggestionChip(
                    modifier = Modifier.padding(Paddings.xsmall),
                    onClick = {
                        chipState.value = chipState.value.toMutableList().also { state ->
                            state[it] = !state[it]
                        }
                    },
                    label = {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = contentList[it],
                            color = if (chipState.value[it]) Color.White else Color.LightGray,
                        )
                    },
                    colors = SuggestionChipDefaults.suggestionChipColors(containerColor = if (chipState.value[it]) PrimaryBlack else Color.White),
                )
            }
        }
    }
}
