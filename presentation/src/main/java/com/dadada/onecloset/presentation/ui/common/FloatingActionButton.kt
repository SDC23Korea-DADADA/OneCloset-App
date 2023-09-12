package com.dadada.onecloset.presentation.ui.common

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.dadada.onecloset.presentation.ui.theme.PrimaryBlack

@Composable
fun CustomFloatingActionButton(modifier: Modifier = Modifier, icon: ImageVector, onClick: () -> Unit) {
    FloatingActionButton(
        modifier = Modifier.padding(bottom = 88.dp),
        contentColor = Color.White,
        containerColor = PrimaryBlack,
        onClick = { onClick() }
    ) {
        Icon(imageVector = icon, contentDescription = "fab")
    }
}