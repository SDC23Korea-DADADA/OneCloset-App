package com.dadada.onecloset.presentation.ui.fitting.component.item

import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.dadada.onecloset.presentation.ui.theme.roundedSquareSmallModifier

@Composable
fun FittingSelectedClothesItem(
    modifier: Modifier = Modifier,
    imageUri: Uri,
    onClick: () -> Unit,
) {
    Box(
        modifier = roundedSquareSmallModifier
            .size(100.dp)
            .clickable(onClick = onClick),
    ) {
        AsyncImage(
            modifier = Modifier.fillMaxSize(),
            model = imageUri,
            contentDescription = "의류 사진",
            contentScale = ContentScale.Crop,
        )
    }
}
