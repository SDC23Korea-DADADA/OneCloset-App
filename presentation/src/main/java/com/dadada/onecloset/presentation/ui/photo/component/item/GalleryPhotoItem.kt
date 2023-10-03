package com.dadada.onecloset.presentation.ui.photo.component.item

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.dadada.onecloset.presentation.R

@Composable
fun GalleryPhotoItem(url: Uri, idx: Int, isChecked: Boolean, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .background(Color.White)
            .aspectRatio(1f)
            .border(1.dp, Color.White)
            .clickable {
                onClick()
            },
    ) {
        AsyncImage(
            modifier = Modifier
                .align(Alignment.Center),
            model = url,
            contentDescription = "의류 사진",
            contentScale = ContentScale.Crop,
        )

        val icon = if (isChecked) R.drawable.ic_checked else R.drawable.ic_unchecked

        Image(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(4.dp),
            painter = painterResource(id = icon),
            contentDescription = "Checked",
        )
    }
}
