package com.dadada.onecloset.presentation.ui.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.dadada.onecloset.presentation.ui.theme.BackGround
import com.dadada.onecloset.presentation.ui.theme.Typography

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
fun TwoButtonDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogText: String,
) {
    AlertDialog(
        title = { Text(text = dialogTitle, style = Typography.titleMedium.copy(fontWeight = FontWeight.ExtraBold)) },
        text = { Text(text = dialogText) },
        onDismissRequest = { onDismissRequest() },
        confirmButton = {
            TextButton(onClick = { onConfirmation() }) {
                Text("확인", fontWeight = FontWeight.ExtraBold)
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismissRequest() }) {
                Text("닫기", fontWeight = FontWeight.ExtraBold)
            }
        },
        containerColor = Color.White
    )
}

@Composable
fun SelectClosetIconDialog(
    selectedIconIdx: MutableState<Int>,
    selectedColor: MutableState<Color>,
    iconResIds: List<Int>,
    colors: List<Color>,
    onDismissRequest: () -> Unit,
) {
    var showPalette by remember { mutableStateOf(true) }
    AlertDialog(containerColor = BackGround,
        onDismissRequest = { onDismissRequest() },
        title = {
            SelectClosetIconDialogHeader(showPalette = showPalette, selectedColor = selectedColor) {
                showPalette = !showPalette
            }
        },
        text = {
            SelectClosetIconDialogBody(
                showPalette = showPalette,
                selectedIconIdx = selectedIconIdx,
                selectedColor = selectedColor,
                iconResIds = iconResIds,
                colors = colors,
            )
        },
        confirmButton = {

        })
}

@Composable
fun SelectClosetIconDialogHeader(
    showPalette: Boolean,
    selectedColor: MutableState<Color>,
    onClick: () -> Unit
) {
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
        DropDownRow(
            component = {
                ColorIconItem(color = selectedColor.value, selectedColor = selectedColor)
            },
            reverse = showPalette,
            onClick = { onClick() }
        )
    }
}

@Composable
fun SelectClosetIconDialogBody(
    showPalette: Boolean,
    selectedIconIdx: MutableState<Int>,
    selectedColor: MutableState<Color>,
    iconResIds: List<Int>,
    colors: List<Color>,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        LazyVerticalGrid(columns = GridCells.Fixed(5)) {
            items(iconResIds.size) {
                RoundedSquareIconItem(
                    modifier = roundedSquareSmallModifier.clickable { selectedIconIdx.value = it },
                    icon = iconResIds[it],
                    backGroundTint = selectedColor.value
                )
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
}

