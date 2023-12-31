package com.dadada.onecloset.presentation.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.dadada.onecloset.presentation.ui.theme.LottieBackGroundGray
import com.dadada.onecloset.presentation.ui.theme.Typography

@Composable
fun RoundedSquare(title: String, content: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(26.dp))
            .background(Color.White)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {

        Spacer(modifier = Modifier.size(4.dp))
        Column {
            Text(text = title, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = content, color = Color.DarkGray)
        }
    }
}

@Composable
fun ListRoundedSquare(list: List<Int>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(26.dp))
            .background(Color.White)
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        list.forEach {
            ClickableRow(it) {

            }
        }
    }
}
