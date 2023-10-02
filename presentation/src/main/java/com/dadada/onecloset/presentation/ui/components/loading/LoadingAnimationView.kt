package com.dadada.onecloset.presentation.ui.components.loading

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.dadada.onecloset.presentation.ui.theme.Paddings
import com.dadada.onecloset.presentation.ui.theme.TextGray
import com.dadada.onecloset.presentation.ui.theme.Typography
import com.dadada.onecloset.presentation.ui.utils.LottieLoader

@Composable
fun LoadingAnimationView(
    modifier: Modifier = Modifier,
    animation: Int,
    text: String,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Gray.copy(alpha = 0.7f)) // 회색 틴트 처리
            .zIndex(1f) // Z-index 설정, 1f 이상의 값이면 다른 오브젝트 위에 올라가게 됩니다.
            .pointerInput(Unit) { detectTapGestures {} },
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .clip(RoundedCornerShape(26.dp))
                .background(Color.White)
                .padding(Paddings.xlarge)
                .aspectRatio(1.5f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            LottieLoader(
                modifier = modifier
                    .fillMaxWidth(0.8f)
                    .weight(1f)
                    .padding(Paddings.xlarge),
                source = animation,
            )
            Spacer(modifier = Modifier.height(Paddings.large))
            Text(
                text = text,
                style = Typography.titleMedium.copy(fontWeight = FontWeight.Bold, color = TextGray),
            )
        }
    }
}
