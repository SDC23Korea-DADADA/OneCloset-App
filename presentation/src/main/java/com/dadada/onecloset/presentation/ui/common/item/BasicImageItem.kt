package com.dadada.onecloset.presentation.ui.common.item

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.dadada.onecloset.presentation.ui.theme.roundedSquareSmallModifier

@Composable
fun BasicImageItem(
    modifier: Modifier = Modifier,
    imageUri: Uri,
    icon: Int?,
    isUploading: Boolean = true,
    onClick: () -> Unit,
) {
    Box(
        modifier = roundedSquareSmallModifier
            .aspectRatio(1f)
            .clickable(onClick = onClick),

    ) {
        AsyncImage(
            modifier = Modifier.fillMaxSize(),
            model = imageUri,
            contentDescription = "의류 사진",
            contentScale = ContentScale.Crop,
        )

        if (!isUploading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.LightGray.copy(alpha = 0.5f)),
            ) {
                Text(
                    text = "등록중",
                    modifier = Modifier.align(Alignment.Center),
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                )
            }
        }

        icon?.let {
            Image(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(4.dp),
                painter = painterResource(id = it),
                contentDescription = "Checked",
            )
        }
    }
}
