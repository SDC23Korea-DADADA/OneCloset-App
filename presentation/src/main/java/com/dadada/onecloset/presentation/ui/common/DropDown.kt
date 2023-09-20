package com.dadada.onecloset.presentation.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun DropDownMenu(
    modifier: Modifier = Modifier,
    expanded: Boolean,
    onClickEdit: () -> Unit,
    onClickDelete: () -> Unit,
    onDismissRequest: () -> Unit
) {
    MaterialTheme(shapes = MaterialTheme.shapes.copy(extraSmall = RoundedCornerShape(20.dp))) {
        DropdownMenu(
            modifier = Modifier.background(Color.White),
            expanded = expanded,
            onDismissRequest = { onDismissRequest() },
        ) {
            DropdownMenuItem(onClick = {
                onClickEdit()
                onDismissRequest()
            }, text = { Text("수정") })
            DropdownMenuItem(onClick = {
                onClickDelete()
                onDismissRequest()
            }, text = { Text("삭제") })
        }
    }
}