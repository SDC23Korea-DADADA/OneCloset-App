package com.dadada.onecloset.presentation.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun LottieLoader(modifier: Modifier, source: Int) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(source))
    LottieAnimation(
        modifier = modifier,
        composition = composition,
        contentScale = ContentScale.FillWidth   ,
        iterations = LottieConstants.IterateForever
    )
}
