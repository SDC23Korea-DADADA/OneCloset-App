package com.dadada.onecloset.presentation.ui.fitting.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.core.net.toUri
import com.dadada.onecloset.domain.model.clothes.ClothesInfo
import com.dadada.onecloset.presentation.ui.components.DropDownRow
import com.dadada.onecloset.presentation.ui.components.FittingDropDownMenu
import com.dadada.onecloset.presentation.ui.components.roundedSquareLargeModifier
import com.dadada.onecloset.presentation.ui.theme.Paddings
import com.dadada.onecloset.presentation.ui.theme.Typography
import com.dadada.onecloset.presentation.ui.utils.FittingEmptyItem
import java.text.SimpleDateFormat
import java.util.Date

private const val TAG = "View"

@Composable
fun FittingSelectedClothListView(
    modifier: Modifier = Modifier,
    clothList: List<ClothesInfo>,
    modeIdx: Int,
    emptyItemList: MutableState<List<FittingEmptyItem>>,
    selectedItemList: List<Int>,
    onClickDropDown: (Int) -> Unit,
) {
    var modeClick by remember { mutableStateOf(false) }
    val modeTitleList = listOf<String>("상의", "하의", "한벌옷", "상하의")

    Column(
        modifier = roundedSquareLargeModifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(modifier = Modifier.align(Alignment.End)) {
            DropDownRow(
                component = {
                    Text(
                        text = modeTitleList[modeIdx],
                        style = Typography.titleMedium.copy(fontWeight = FontWeight.ExtraBold)
                    )
                },
                reverse = modeClick,
                onClick = { modeClick = !modeClick }
            )
            if (modeClick) {
                FittingDropDownMenu(
                    expanded = modeClick,
                    modeTitleList = modeTitleList,
                    onClick = { onClickDropDown(it) }) {
                    modeClick = !modeClick
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Paddings.large),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            selectedItemList.forEachIndexed { index, id ->
                if (id == -1) {
                    EmptyClothItem(
                        icon = emptyItemList.value[index].icon,
                        content = emptyItemList.value[index].content
                    )
                } else {
                    val cloth = clothList.find { it.clothesId == id }
                    if (cloth != null) {
                        SelectClothItem(imageUri = cloth.thumnailUrl.toUri(), onClick = {})
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyDatePickerDialog(
    onDateSelected: (String) -> Unit,
    onDismiss: () -> Unit,
    onPass: (String) -> Unit,
    onPlan: (String) -> Unit
) {
    val datePickerState = rememberDatePickerState()

    val selectedDate = datePickerState.selectedDateMillis?.let {
        convertMillisToDate(it)
    } ?: ""

    MaterialTheme(colorScheme = com.dadada.onecloset.presentation.ui.theme.LightColorScheme.copy()) {
        DatePickerDialog(
            colors = DatePickerDefaults.colors(containerColor = Color.White),
            onDismissRequest = { onDismiss() },
            confirmButton = {
                Button(onClick = {
                    onPlan(selectedDate)
                    onDismiss()
                }

                ) {
                    Text(text = "등록하기")
                }
            },
            dismissButton = {
                Button(onClick = {
                    onPass(selectedDate)
                    onDismiss()
                }) {
                    Text(text = "건너뛰기")
                }
            }
        ) {
            DatePicker(
                state = datePickerState
            )
        }
    }
}

private fun convertMillisToDate(millis: Long): String {
    val formatter = SimpleDateFormat("yyyy-MM-dd")
    return formatter.format(Date(millis))
}
