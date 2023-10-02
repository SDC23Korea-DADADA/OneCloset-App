package com.dadada.onecloset.presentation.ui.components.row

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.dadada.onecloset.presentation.ui.components.button.DropDownButton

@Composable
fun DropDownRow(
    modifier: Modifier = Modifier,
    component: @Composable () -> Unit,
    reverse: Boolean,
    onClick: () -> Unit,
) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        component()
        DropDownButton(reverse = reverse) {
            onClick()
        }
    }
}
