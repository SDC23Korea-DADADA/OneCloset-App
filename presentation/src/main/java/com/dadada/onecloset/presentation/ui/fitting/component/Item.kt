package com.dadada.onecloset.presentation.ui.fitting.component

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.dadada.onecloset.presentation.ui.theme.roundedSquareSmallModifier
import com.dadada.onecloset.presentation.ui.theme.BackGround
import com.dadada.onecloset.presentation.ui.theme.Typography

@Composable
fun EmptyClothItem(icon: Int, content: String) {
    Column(
        modifier = roundedSquareSmallModifier
            .size(100.dp)
            .background(BackGround),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(painter = painterResource(id = icon), contentDescription = "", tint = Color.LightGray)
        Spacer(modifier = Modifier.size(4.dp))
        Text(text=content, style = Typography.bodySmall.copy(color = Color.LightGray))
    }
}

@Composable
fun SelectClothItem(
    modifier: Modifier = Modifier,
    imageUri: Uri,
    onClick: () -> Unit
) {
    Box(
        modifier = roundedSquareSmallModifier
            .size(100.dp)
            .clickable(onClick = onClick)

    ) {
        AsyncImage(
            modifier = Modifier.fillMaxSize(),
            model = imageUri,
            contentDescription = "의류 사진",
            contentScale = ContentScale.Crop
        )
    }
}