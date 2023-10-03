package com.dadada.onecloset.presentation.ui.fitting.component.menu

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
fun FittingDropDownMenu(
    modifier: Modifier = Modifier,
    expanded: Boolean,
    modeTitleList: List<String>,
    onClick: (Int) -> Unit,
    onDismissRequest: () -> Unit,
) {
    MaterialTheme(shapes = MaterialTheme.shapes.copy(extraSmall = RoundedCornerShape(20.dp))) {
        DropdownMenu(
            modifier = Modifier.background(Color.White),
            expanded = expanded,
            onDismissRequest = { onDismissRequest() },
        ) {
            modeTitleList.forEachIndexed { index, content ->
                DropdownMenuItem(onClick = {
                    onClick(index)
                    onDismissRequest()
                }, text = { Text(content) })
            }
        }
    }
}
