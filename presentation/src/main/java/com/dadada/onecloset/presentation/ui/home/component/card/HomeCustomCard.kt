package com.dadada.onecloset.presentation.ui.home.component.card

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.dadada.onecloset.presentation.ui.utils.LottieLoader
import com.dadada.onecloset.presentation.ui.theme.Corner
import com.dadada.onecloset.presentation.ui.theme.LottieBackGroundGray
import com.dadada.onecloset.presentation.ui.theme.Paddings
import com.dadada.onecloset.presentation.ui.theme.Typography

@Composable
fun HomeCustomCard(
    modifier: Modifier = Modifier,
    title: String,
    content: String,
    animation: Int,
    onClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .clickable { onClick() }
            .padding(Paddings.xlarge),
    ) {
        Column(modifier = Modifier.padding(Paddings.medium)) {
            Text(text = title, style = Typography.titleMedium)
            Text(
                modifier = Modifier.padding(vertical = Paddings.small),
                text = content,
                style = Typography.bodyMedium,
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(2f)
                .clip(RoundedCornerShape(Corner.large))
                .background(LottieBackGroundGray),
        ) {
            LottieLoader(
                source = animation,
                modifier = Modifier
                    .fillMaxHeight()
                    .aspectRatio(1f)
                    .align(Alignment.CenterEnd),
            )
        }
    }
}
