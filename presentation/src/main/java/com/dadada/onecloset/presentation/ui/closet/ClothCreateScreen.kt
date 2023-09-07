package com.dadada.onecloset.presentation.ui.closet

import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil.compose.AsyncImage

@Composable
fun ClothCreateScreen(photoUri: Uri) {
    Box(modifier = Modifier.fillMaxSize()) {
        AsyncImage(model = photoUri, contentDescription = "")
    }
}
