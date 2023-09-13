package com.dadada.onecloset.presentation.ui.common

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate

@Composable
fun DropDownButton(reverse: Boolean, onClick: () -> Unit) {
    val rotation by animateFloatAsState(
        targetValue = if (reverse) 180f else 0f,
        animationSpec = tween(300), label = "" // 300ms 동안 애니메이션
    )

    IconButton(onClick = { onClick() }) {
        Icon(
            imageVector = Icons.Default.ArrowDropDown,
            contentDescription = "목록",
            modifier = Modifier.rotate(rotation)
        )
    }
}