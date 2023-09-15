package com.dadada.onecloset.presentation.ui.photo

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil.compose.AsyncImage

@Composable
fun PhotoScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        AsyncImage(modifier = Modifier.fillMaxSize(), model = "https://static.lookpin.co.kr/20221106161042-c205/27c453f3cc4e23abe11cd47039a40a41.jpg", contentDescription = "")
    }
}