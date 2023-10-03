package com.dadada.onecloset.presentation.ui.common.dialog

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.dadada.onecloset.presentation.ui.theme.Typography

@Composable
fun TwoButtonDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogText: String,
) {
    AlertDialog(
        title = {
            Text(
                text = dialogTitle,
                style = Typography.titleSmall,
            )
        },
        text = { Text(text = dialogText) },
        onDismissRequest = { onDismissRequest() },
        confirmButton = {
            TextButton(onClick = { onConfirmation() }) {
                Text("확인", style = Typography.titleSmall)
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismissRequest() }) {
                Text("닫기", style = Typography.titleSmall)
            }
        },
        containerColor = Color.White,
    )
}
