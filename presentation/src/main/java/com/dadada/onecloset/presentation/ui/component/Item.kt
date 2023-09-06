package com.dadada.onecloset.presentation.ui.component

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.dadada.onecloset.domain.model.Closet
import com.dadada.onecloset.domain.model.Cloth
import com.dadada.onecloset.presentation.R
import com.dadada.onecloset.presentation.ui.theme.BackGround
import com.dadada.onecloset.presentation.ui.theme.Typography

@Composable
fun ClosetItemView(item: Closet, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .aspectRatio(1f)
            .clickable {
                onClick()
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        ClosetIcon(modifier = Modifier, icon = R.drawable.ic_date, color = Color(item.iconColor))
        Spacer(modifier = Modifier.size(2.dp))
        Text(
            text = item.name,
            style = Typography.titleSmall.copy(fontWeight = FontWeight.ExtraBold)
        )
    }
}

@Composable
fun ClothItemView(item: Cloth, onClick: () -> Unit) {
    Box(modifier = Modifier
        .background(Color.White)
        .padding(4.dp)
        .aspectRatio(1f)
        .border(1.dp, BackGround, RoundedCornerShape(20.dp))
        .clickable {
            onClick()
        }) {
        AsyncImage(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(4.dp),
            model = item.thumbnailImg,
            contentDescription = "의류 사진"
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