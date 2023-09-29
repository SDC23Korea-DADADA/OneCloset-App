package com.dadada.onecloset.presentation.ui.common

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.dadada.onecloset.presentation.ui.theme.Paddings
import com.dadada.onecloset.presentation.ui.theme.TextGray
import com.dadada.onecloset.presentation.ui.theme.Typography

@Composable
fun LoadingView(
    modifier: Modifier = Modifier,
    animation: Int,
    text: String
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Gray.copy(alpha = 0.7f)) // 회색 틴트 처리
            .zIndex(1f) // Z-index 설정, 1f 이상의 값이면 다른 오브젝트 위에 올라가게 됩니다.
        ,
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .clip(RoundedCornerShape(26.dp))
                .background(Color.White)
                .padding(Paddings.xlarge)
                .aspectRatio(1.5f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LottieLoader(
                modifier = modifier
                    .fillMaxWidth(0.8f)
                    .weight(1f)
                    .padding(Paddings.xlarge),
                source = animation
            )
            Spacer(modifier = Modifier.height(Paddings.large))
            Text(
                text = text,
                style = Typography.titleMedium.copy(fontWeight = FontWeight.Bold, color = TextGray)
            )
        }
    }
}

@Composable
fun GalaxyLoadingView(modifier: Modifier = Modifier) {
    val rotate = rememberInfiniteTransition(label = "").animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ), label = ""
    )

    val bounce = rememberInfiniteTransition(label = "").animateFloat(
        initialValue = 0.2f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = 500
                0.3f at 150
                1f at 500
            },
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )
    Box(
        modifier = modifier
            .fillMaxSize()
            .zIndex(1f) // Z-index 설정, 1f 이상의 값이면 다른 오브젝트 위에 올라가게 됩니다.
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .rotate(rotate.value)
                .size(48.dp)
        ) {
            Ball(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .offset(y = (12.dp * bounce.value)),
                color = Color(0xFF00d593)
            )
            Ball(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .offset(y = (-12.dp * bounce.value)),
                color = Color(0xFF0481ff)
            )
            Ball(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .offset(x = (12.dp * bounce.value)),
                color = Color(0xFF0481ff)
            )
            Ball(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .offset(x = (-12.dp * bounce.value)),
                color = Color(0xFF0481ff)
            )
        }
    }
}

@Composable
fun Ball(
    modifier: Modifier = Modifier,
    color: Color,
    scale: Float = 1f
) {
    Box(
        modifier = modifier
            .background(color, shape = CircleShape)
            .scale(scale)
            .size(10.dp)
    )
}

