package com.dadada.onecloset.presentation.ui.fitting.component

import android.util.Log
import androidx.compose.foundation.background
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
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
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
import com.dadada.onecloset.presentation.ui.common.DropDownRow
import com.dadada.onecloset.presentation.ui.common.FittingDropDownMenu
import com.dadada.onecloset.presentation.ui.common.roundedSquareLargeModifier
import com.dadada.onecloset.presentation.ui.theme.Paddings
import com.dadada.onecloset.presentation.ui.theme.Typography
import com.dadada.onecloset.presentation.ui.utils.FittingEmptyItem
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

private const val TAG = "View"

@Composable
fun FittingSelectedClothListView(
    modifier: Modifier = Modifier,
    clothList: List<ClothesInfo>,
    modeIdx: Int,
    emptyItemList: List<FittingEmptyItem>,
    selectedItemList: List<ClothesInfo>,
    onClickDropDown: (Int) -> Unit,
) {
    var modeClick by remember { mutableStateOf(false) }
    val modeTitleList = listOf<String>("상하의", "상의", "하의", "한벌옷")

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
            selectedItemList.forEachIndexed { index, cloth ->
                if (cloth.clothesId == -1) {
                    EmptyClothItem(
                        icon = emptyItemList[index].icon,
                        content = emptyItemList[index].content
                    )
                } else {
                    SelectClothItem(imageUri = cloth.thumnailUrl.toUri(), onClick = {})
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
    val datePickerState = rememberDatePickerState(selectableDates = object : SelectableDates {
        override fun isSelectableDate(utcTimeMillis: Long): Boolean {
            return utcTimeMillis <= System.currentTimeMillis()
        }
    })

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
                    Text(text = "계획하기")
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
