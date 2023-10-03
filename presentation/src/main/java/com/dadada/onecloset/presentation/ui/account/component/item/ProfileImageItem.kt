package com.dadada.onecloset.presentation.ui.account.component.item

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.core.net.toUri
import coil.compose.AsyncImage

@Composable
fun ProfileImageItem(modifier: Modifier = Modifier, url: String) {
    Box(
        modifier = modifier
            .clip(CircleShape)
            .background(Color.White),
    ) {
        AsyncImage(
            modifier = Modifier
                .align(Alignment.Center),
            model = url.toUri(),
            contentDescription = "사진",
            contentScale = ContentScale.Crop,
        )
    }
}