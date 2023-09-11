package com.dadada.onecloset.presentation.ui.component

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.dadada.onecloset.domain.model.Closet
import com.dadada.onecloset.presentation.R
import com.dadada.onecloset.presentation.ui.theme.BackGround
import com.dadada.onecloset.presentation.ui.theme.Typography

@Composable
fun ClosetItemViewWithName(item: Closet, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .aspectRatio(1f)
            .clickable {
                onClick()
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        ClosetItem(modifier = Modifier, icon = R.drawable.ic_date, color = Color(item.iconColor))
        Spacer(modifier = Modifier.size(2.dp))
        Text(
            text = item.name,
            style = Typography.titleSmall.copy(fontWeight = FontWeight.ExtraBold)
        )
    }
}

@Composable
fun ClosetItem(modifier: Modifier = Modifier, icon: Int, color: Color) {
    Box(
        modifier = modifier
            .padding(4.dp)
            .size(48.dp)
            .aspectRatio(1f)
            .clip(RoundedCornerShape(8.dp))
            .background(color)
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
fun ClothItemView(modifier: Modifier, imageUri: Uri, onClick: () -> Unit) {
    Box(modifier = modifier
        .background(Color.White)
        .aspectRatio(1f)
        .clickable {
            onClick()
        }) {
        AsyncImage(
            model = imageUri,
            contentDescription = "의류 사진",
            contentScale = ContentScale.Crop
        )
    }
}

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
fun ListItem(content: String, onClick: () -> Unit) {
    Column(
        modifier = Modifier.clickable { onClick() }
    ) {
        ListItem(
            headlineContent = { Text(content) },
            trailingContent = {
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowRight,
                    contentDescription = "",
                    tint = BackGround
                )
            },
            colors = ListItemDefaults.colors(
                containerColor = Color.White
            ),

        )
    }
}

