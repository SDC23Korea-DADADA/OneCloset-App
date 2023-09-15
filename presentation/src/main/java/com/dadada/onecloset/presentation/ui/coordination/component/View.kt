package com.dadada.onecloset.presentation.ui.coordination.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.navigation.NavHostController
import com.dadada.onecloset.domain.model.Closet
import com.dadada.onecloset.presentation.R
import com.dadada.onecloset.presentation.ui.ClosetDetailNav
import com.dadada.onecloset.presentation.ui.common.RoundedSquareIconWithTitleItem
import com.dadada.onecloset.presentation.ui.common.RoundedSquareImageItem
import com.dadada.onecloset.presentation.ui.common.roundedSquareLargeModifier
import com.dadada.onecloset.presentation.ui.common.roundedSquareMediumModifier
import com.dadada.onecloset.presentation.ui.theme.PointGreen
import com.dadada.onecloset.presentation.ui.theme.PrimaryBlue


@Composable
fun SelectCodiView(onClickRecord: () -> Unit, onClickPlan: () -> Unit) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp, top = 8.dp, start = 16.dp, end = 16.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        RoundedSquareIconWithTitleItem(
            modifier = Modifier.size(80.dp),
            title = "기록",
            icon = R.drawable.ic_daily,
            backGroundTint = PrimaryBlue,
            onClick = onClickRecord
        )

        RoundedSquareIconWithTitleItem(
            modifier = Modifier.size(80.dp),
            title = "계획",
            icon = R.drawable.ic_lamp,
            backGroundTint = PointGreen,
            onClick = onClickPlan
        )
    }
}

@Composable
fun CoordinationListView(navHostController: NavHostController, imageList: List<String>) {
    Box(
        modifier = roundedSquareLargeModifier.aspectRatio(1f)
    ) {
        LazyVerticalGrid(
            modifier = Modifier
                .align(Alignment.Center),
            columns = GridCells.Fixed(3),
        ) {
            items(imageList.size) {
                RoundedSquareImageItem(
                    modifier = roundedSquareMediumModifier,
                    imageUri = imageList[it].toUri(),
                    icon = null,
                    onClick = { },
                )
            }
        }
    }
}