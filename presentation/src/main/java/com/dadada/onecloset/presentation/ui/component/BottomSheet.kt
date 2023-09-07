package com.dadada.onecloset.presentation.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.dadada.onecloset.presentation.ui.theme.Typography
import com.dadada.onecloset.presentation.ui.theme.iconBlue
import com.dadada.onecloset.presentation.ui.utils.ColorEnum
import com.dadada.onecloset.presentation.ui.utils.IconEnum

@Composable
fun BottomSheetAddCloset() {
    val iconResIds = IconEnum.values().map { it.resId }
    val colors = ColorEnum.values().map { it.color }

    var textValue by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }
    var selectedIconIdx = remember { mutableStateOf(0) }
    val selectedColor = remember { mutableStateOf(iconBlue) }


    if (showDialog) {
        SelectClosetIconDialog(
            selectedIconIdx = selectedIconIdx,
            selectedColor = selectedColor,
            iconResIds = iconResIds,
            colors = colors,
            onDismissRequest = { showDialog = !showDialog },
        )
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "옷장 등록",
            style = Typography.titleMedium.copy(fontWeight = FontWeight.ExtraBold),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.size(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "대표 아이콘", style = Typography.titleSmall.copy(fontWeight = FontWeight.Bold))
            DropDownRow(
                component = {
                    ClosetItem(
                        modifier = Modifier,
                        icon = iconResIds[selectedIconIdx.value],
                        color = selectedColor.value
                    )
                },
                reverse = showDialog,
                onClick = { showDialog = !showDialog }
            )
        }

        Spacer(modifier = Modifier.size(12.dp))

        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "이름", style = Typography.titleSmall.copy(fontWeight = FontWeight.Bold))
            OutlinedTextField(
                value = textValue,
                onValueChange = { textValue = it },
                modifier = Modifier.fillMaxWidth(0.5f) // 원하는 너비 지정
            )
        }

        Spacer(modifier = Modifier.size(16.dp))

        Button(modifier = Modifier.fillMaxWidth(), onClick = { /*TODO*/ }) {
            Text(text = "등록")
        }
    }
}