package com.dadada.onecloset.presentation.ui.utils

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun ShowToast(text: String) {
    val context = LocalContext.current
    Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
}