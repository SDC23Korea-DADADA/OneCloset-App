package com.dadada.onecloset.presentation.ui.closet

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.navigation.NavHostController
import com.dadada.onecloset.domain.model.Cloth
import com.dadada.onecloset.presentation.ui.component.ClothItemView
import com.dadada.onecloset.presentation.ui.component.RoundedShape

@Composable
fun ClothScreen(navHostController: NavHostController) {
    val list = listOf("세탁", "건조", "에어드레서")
    val contentList = listOf(
        "울 소재의 옷은 울 전용 세제를 넣어 울 전용 코스를 이용해보세요!",
        "울 소재의 옷은 울 전용 세제를 넣어 울 전용 코스를 이용해보세요!",
        "울 소재의 옷은 울 전용 세제를 넣어 울 전용 코스를 이용해보세요!"
    )

    val item = Cloth()
    Column {
        ClothItemView(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(26.dp)), imageUri = item.url.toUri()
        ) {

        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.weight(1f))
            Column() {
                list.forEachIndexed { index, s ->
                    RoundedShape(title = s, content = contentList[index])
                    Spacer(modifier = Modifier.size(12.dp))
                }
            }
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}