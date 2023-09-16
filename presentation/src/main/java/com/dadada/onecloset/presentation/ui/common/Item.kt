package com.dadada.onecloset.presentation.ui.common

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.dadada.onecloset.presentation.R
import com.dadada.onecloset.presentation.ui.theme.Typography

@Composable
fun ColorIconItem(color: Color, selectedColor: MutableState<Color>) {
    Box(modifier = Modifier
        .padding(4.dp)
        .clip(CircleShape)
        .size(32.dp)
        .background(color)
        .clickable { selectedColor.value = color })
}

@Composable
fun GalleryPhotoItem(url: Uri, idx: Int, isChecked: Boolean, onClick: () -> Unit) {
    Box(modifier = Modifier
        .background(Color.White)
        .aspectRatio(1f)
        .border(1.dp, Color.White)
        .clickable {
            onClick()
        }) {

        AsyncImage(
            modifier = Modifier
                .align(Alignment.Center),
            model = url,
            contentDescription = "의류 사진",
            contentScale = ContentScale.Crop
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

@Composable
fun PhotoItem(onClick: () -> Unit) {
    Column(modifier = Modifier
        .background(Color.White)
        .aspectRatio(1f)
        .border(1.dp, Color.White)
        .clickable {
            onClick()
        }, horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Image(
            modifier = Modifier
                .padding(4.dp),
            painter = painterResource(id = R.drawable.ic_camera),
            contentDescription = "Checked",
        )
        Text(text = "사진 찍기")
    }
}

@Composable
fun CustomListItem(content: String, onClick: () -> Unit) {
    ListItem(
        modifier = Modifier.clickable { onClick() },
        headlineContent = { Text(content) },
        trailingContent = {
            Icon(
                imageVector = Icons.Filled.KeyboardArrowRight,
                contentDescription = "",
                tint = Color.LightGray
            )
        },
        colors = ListItemDefaults.colors(
            containerColor = Color.White
        ),
    )
}

@Composable
fun CircleImageView(modifier: Modifier = Modifier, url: String) {
    Box(
        modifier = modifier
            .clip(CircleShape)
            .background(Color.White)
    ) {
        AsyncImage(
            modifier = Modifier
                .align(Alignment.Center),
            model = url,
            contentDescription = "사진",
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun RoundedSquareIconItem(
    modifier: Modifier = roundedSquareSmallModifier,
    icon: Int,
    backGroundTint: Color
) {
    Box(
        modifier = modifier
            .size(48.dp)
            .background(backGroundTint)
    ) {
        Icon(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(4.dp),
            painter = painterResource(id = icon),
            contentDescription = "아이콘",
            tint = Color.White
        )
    }
}

@Composable
fun RoundedSquareIconWithTitleItem(
    modifier: Modifier = Modifier,
    title: String,
    icon: Int,
    backGroundTint: Color,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier.clickable(onClick = onClick),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        RoundedSquareIconItem(icon = icon, backGroundTint = backGroundTint)
        Spacer(modifier = Modifier.size(2.dp))
        Text(
            text = title,
            style = Typography.titleSmall.copy(fontWeight = FontWeight.ExtraBold)
        )
    }
}

@Composable
fun RoundedSquareImageItem(
    modifier: Modifier = Modifier,
    imageUri: Uri,
    icon: Int?,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .aspectRatio(1f)
            .border(1.dp, Color.White)
            .clickable(onClick = onClick)

    ) {
        AsyncImage(
            modifier = roundedSquareLargeModifier.align(Alignment.Center),
            model = imageUri,
            contentDescription = "의류 사진",
            contentScale = ContentScale.Crop
        )


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