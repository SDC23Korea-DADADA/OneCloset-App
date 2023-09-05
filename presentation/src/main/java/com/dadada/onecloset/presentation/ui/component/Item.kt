package com.dadada.onecloset.presentation.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import com.dadada.onecloset.domain.model.Closet
import com.dadada.onecloset.presentation.ui.theme.Typography

@Composable
fun ClosetItemView(item: Closet) {
    Column(
        modifier = Modifier.aspectRatio(1f)
            .clickable {

            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(painter = painterResource(id = item.icon), contentDescription = "아이콘")
        Text(text = item.name, style = Typography.titleSmall.copy(fontWeight = FontWeight.ExtraBold))
    }
}