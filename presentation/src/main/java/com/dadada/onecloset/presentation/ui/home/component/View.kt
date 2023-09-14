package com.dadada.onecloset.presentation.ui.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.dadada.onecloset.presentation.ui.common.LottieLoader
import com.dadada.onecloset.presentation.ui.theme.LottieBackGroundGray
import com.dadada.onecloset.presentation.ui.theme.Typography

@Composable
fun HomeMainFeatureCard(
    modifier: Modifier = Modifier,
    title: String,
    content: String,
    animation: Int,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
            .clickable(onClick = onClick)
    ) {
        Text(text = title, style = Typography.titleMedium.copy(fontWeight = FontWeight.ExtraBold))
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = content)
        Spacer(modifier = Modifier.height(8.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .clip(RoundedCornerShape(26.dp))
                .background(LottieBackGroundGray)
        ) {
            LottieLoader(
                source = animation,
                modifier = Modifier
                    .size(150.dp)
                    .align(Alignment.CenterEnd)
            )
        }
    }
}

