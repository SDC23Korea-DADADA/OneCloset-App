package com.dadada.onecloset.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.dadada.onecloset.presentation.R
import com.dadada.onecloset.presentation.ui.theme.BackGround
import com.dadada.onecloset.presentation.ui.theme.Typography
import com.dadada.onecloset.presentation.ui.utils.ColorEnum
import com.dadada.onecloset.presentation.ui.utils.IconEnum

@Composable
fun AlertDialogWithTwoButton(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogText: String,
    icon: ImageVector,
) {
    AlertDialog(
        icon = { Icon(icon, contentDescription = "Example Icon") },
        title = { Text(text = dialogTitle) },
        text = { Text(text = dialogText) },
        onDismissRequest = { onDismissRequest() },
        confirmButton = {
            TextButton(onClick = { onConfirmation() }) {
                Text("설정")
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismissRequest() }) {
                Text("닫기")
            }
        },
        containerColor = Color.White
    )
}

@Composable
fun AlertDialogIcons(
    showDialog: MutableState<Boolean>,
    selectedIconIdx: MutableState<Int>,
    selectedColor: MutableState<Color>,
    iconResIds: List<Int>,
    colors: List<Color>
) {
    val iconResIds = IconEnum.values().map { it.resId }
    val colors = ColorEnum.values().map { it.color }
    var showPalette by remember { mutableStateOf(true) }

    AlertDialog(containerColor = BackGround,
        onDismissRequest = { showDialog.value = false },
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.padding(end = 4.dp),
                    text = "아이콘 선택",
                    style = Typography.titleMedium
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    ColorIconItem(color = selectedColor.value, selectedColor = selectedColor)
                    IconButton(onClick = { showPalette = !showPalette }) {
                        Icon(
                            painterResource(id = if (showPalette) R.drawable.ic_arrow_drop_up else R.drawable.ic_arrow_drop_down),
                            contentDescription = "Edit Icon"
                        ) // 수정 아이콘
                    }
                }
            }
        },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                LazyVerticalGrid(columns = GridCells.Fixed(5)) {
                    items(iconResIds.size) {
                        IconButton(modifier = Modifier
                            .padding(4.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(selectedColor.value)
                            .aspectRatio(1f),
                            onClick = {
                                selectedIconIdx.value = it
                            }) {
                            Icon(
                                modifier = Modifier.padding(4.dp),
                                painter = painterResource(id = iconResIds[it]),
                                contentDescription = "Icon Option",
                                tint = Color.White
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.size(12.dp))
                if (showPalette) {
                    LazyVerticalGrid(columns = GridCells.Fixed(6)) {
                        items(colors.size) {
                            ColorIconItem(color = colors[it], selectedColor)
                        }
                    }
                } else {
                    LazyVerticalGrid(columns = GridCells.Fixed(6)) {
                        items(colors.size) {
                            ColorIconItem(color = BackGround, selectedColor)
                        }
                    }
                }
            }
        },
        confirmButton = {

        })
}