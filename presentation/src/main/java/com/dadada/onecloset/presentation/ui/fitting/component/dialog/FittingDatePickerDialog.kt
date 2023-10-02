package com.dadada.onecloset.presentation.ui.fitting.component.dialog

import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.dadada.onecloset.presentation.ui.theme.LightColorScheme
import java.text.SimpleDateFormat
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FittingDatePickerDialog(
    onDateSelected: (String) -> Unit,
    onDismiss: () -> Unit,
    onPass: (String) -> Unit,
    onPlan: (String) -> Unit,
) {
    val datePickerState = rememberDatePickerState()

    val selectedDate = datePickerState.selectedDateMillis?.let {
        convertMillisToDate(it)
    } ?: ""

    MaterialTheme(colorScheme = LightColorScheme.copy()) {
        DatePickerDialog(
            colors = DatePickerDefaults.colors(containerColor = Color.White),
            onDismissRequest = { onDismiss() },
            confirmButton = {
                Button(
                    onClick = {
                        onPlan(selectedDate)
                        onDismiss()
                    },
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
            },
        ) {
            DatePicker(
                state = datePickerState,
            )
        }
    }
}

private fun convertMillisToDate(millis: Long): String {
    val formatter = SimpleDateFormat("yyyy-MM-dd")
    return formatter.format(Date(millis))
}
