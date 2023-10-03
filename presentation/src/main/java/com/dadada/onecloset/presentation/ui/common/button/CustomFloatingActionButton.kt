package com.dadada.onecloset.presentation.ui.common.button

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.dadada.onecloset.presentation.ui.theme.Paddings
import com.dadada.onecloset.presentation.ui.theme.PrimaryBlack

@Composable
fun CustomFloatingActionButton(
    modifier: Modifier = Modifier,
    title: String = "",
    icon: ImageVector,
    onClick: () -> Unit,
) {
    FloatingActionButton(
        modifier = modifier,
        contentColor = Color.White,
        containerColor = PrimaryBlack,
        onClick = { onClick() },
    ) {
        Row(
            modifier = Modifier.padding(horizontal = Paddings.medium),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround,
        ) {
            Icon(modifier = Modifier.size(18.dp), imageVector = icon, contentDescription = "fab")
            Text(text = title, fontWeight = FontWeight.Bold)
        }
    }
}
