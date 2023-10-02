package com.dadada.onecloset.presentation.ui.clothes.component.sheet

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.dadada.onecloset.presentation.ui.clothes.component.item.ColorItem
import com.dadada.onecloset.presentation.ui.clothes.component.item.ColorMultiItem
import com.dadada.onecloset.presentation.ui.theme.BackGround
import com.dadada.onecloset.presentation.ui.theme.Typography
import com.dadada.onecloset.presentation.ui.utils.ClothColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectColorBottomSheet(
    modifier: Modifier = Modifier,
    sheetState: SheetState,
    onDismissRequest: () -> Unit,
    onClick: (ClothColor) -> Unit,
) {
    val list = ClothColor.getAllColor()
    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = { onDismissRequest() },
        containerColor = BackGround,
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "색상 선택",
                style = Typography.titleMedium,
                textAlign = TextAlign.Center,
            )

            Spacer(modifier = Modifier.size(12.dp))
            LazyVerticalGrid(
                columns = GridCells.Adaptive(56.dp),
                modifier = Modifier
                    .clip(RoundedCornerShape(26.dp))
                    .background(Color.White),
            ) {
                items(list.size) {
                    // 다채색을 나타내는 아이템인 경우
                    if (it == list.size - 1) {
                        ColorMultiItem(
                            Modifier.clickable {
                                onClick(list[it])
                                onDismissRequest()
                            },
                        )
                    } else {
                        ColorItem(
                            modifier = Modifier.clickable {
                                onClick(list[it])
                                onDismissRequest()
                            },
                            color = list[it].color,
                            name = list[it].name,
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.size(56.dp))
        }
    }
}
