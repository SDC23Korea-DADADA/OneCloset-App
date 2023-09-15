package com.dadada.onecloset.presentation.ui.coordination

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.dadada.onecloset.presentation.ui.common.roundedSquareLargeModifier
import com.dadada.onecloset.presentation.ui.coordination.component.CoordinationListView

@Composable
fun CoordinationListScreen(navHostController: NavHostController) {
    val list = listOf(
        "https://static.lookpin.co.kr/20221106161042-c205/27c453f3cc4e23abe11cd47039a40a41.jpg",
        "https://static.lookpin.co.kr/20221106161042-c205/27c453f3cc4e23abe11cd47039a40a41.jpg",
        "https://static.lookpin.co.kr/20221106161042-c205/27c453f3cc4e23abe11cd47039a40a41.jpg",
        "https://static.lookpin.co.kr/20221106161042-c205/27c453f3cc4e23abe11cd47039a40a41.jpg",
    )

    Column(modifier = roundedSquareLargeModifier) {
        CoordinationListView(navHostController = navHostController, imageList = list)
    }

}
