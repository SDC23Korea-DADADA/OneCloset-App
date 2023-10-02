package com.dadada.onecloset.presentation.ui.clothes.component.row

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.dadada.onecloset.presentation.ui.theme.Paddings
import com.dadada.onecloset.presentation.ui.theme.PrimaryBlack
import com.dadada.onecloset.presentation.ui.theme.TextGray
import com.dadada.onecloset.presentation.ui.theme.Typography

@Composable
fun ClothesAdditionalInfoRow(title: String, contentList: List<String> = listOf()) {
    Column(modifier = Modifier.padding(vertical = Paddings.medium)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(text = title, fontWeight = FontWeight.ExtraBold)
        }

        if (contentList.isNotEmpty() && contentList[0] != "") {
            LazyVerticalGrid(modifier = Modifier.height(44.dp), columns = GridCells.Fixed(4)) {
                items(contentList.size) {
                    if (contentList[it] == "") {
                        return@items
                    }
                    SuggestionChip(
                        modifier = Modifier.padding(Paddings.small),
                        onClick = { /*TODO*/ },
                        label = { Text(text = contentList[it], color = Color.White) },
                        colors = SuggestionChipDefaults.suggestionChipColors(containerColor = PrimaryBlack),
                    )
                }
            }
        } else if (title != "설명") {
            Spacer(modifier = Modifier.size(Paddings.small))
            Text(text = "정보를 등록해주세요.", style = Typography.bodySmall.copy(color = TextGray))
        }
    }
}
