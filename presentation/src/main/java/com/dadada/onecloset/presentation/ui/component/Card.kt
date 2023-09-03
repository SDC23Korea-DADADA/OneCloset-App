package com.dadada.onecloset.presentation.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun CardWithAnimation(title: String, content: String, animation: Int) {
    Column {
        Text(text = title)
        Text(text = content)

    }
}