package com.dadada.onecloset.presentation.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dadada.onecloset.presentation.R
import com.dadada.onecloset.presentation.ui.common.CardWithAnimation

@Composable
fun HomeScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        CardWithAnimation(
            title = "Care",
            content = "의류 이미지로 세탁기, 건조기, 에어 드레서\n코스를 추천 받으세요!",
            animation = R.raw.animation_course
        )

        Spacer(modifier = Modifier.height(24.dp))
        CardWithAnimation(
            title = "Fitting",
            content = "옷장 속 의류 사진으로 나만의 가상 코디를\n만들어보세요!",
            animation = R.raw.animation_fitting
        )
    }
}