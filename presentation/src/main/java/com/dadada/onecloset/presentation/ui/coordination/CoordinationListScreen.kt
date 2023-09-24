package com.dadada.onecloset.presentation.ui.coordination

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.core.net.toUri
import androidx.navigation.NavHostController
import com.dadada.onecloset.domain.model.codi.Codi
import com.dadada.onecloset.domain.model.codi.CodiList
import com.dadada.onecloset.domain.model.codi.Fitting
import com.dadada.onecloset.presentation.ui.common.RoundedSquareImageItem
import com.dadada.onecloset.presentation.ui.common.roundedSquareLargeModifier
import com.dadada.onecloset.presentation.ui.common.roundedSquareMediumModifier
import com.dadada.onecloset.presentation.ui.coordination.component.CoordinationListView

@Composable
fun CoordinationCodiListScreen(navHostController: NavHostController, itemList: List<Codi>) {

    Column(modifier = roundedSquareLargeModifier) {
        Box(
            modifier = roundedSquareLargeModifier.aspectRatio(1f)
        ) {
            LazyVerticalGrid(
                modifier = Modifier
                    .align(Alignment.Center),
                columns = GridCells.Fixed(3),
            ) {
                items(itemList.size) {
                    RoundedSquareImageItem(
                        modifier = roundedSquareMediumModifier,
                        imageUri = itemList[it].thumbnailImg.toUri(),
                        icon = null,
                        onClick = { },
                    )
                }
            }
        }
    }

}


@Composable
fun CoordinationFittingListScreen(navHostController: NavHostController, itemList: List<Fitting>) {


    Column(modifier = roundedSquareLargeModifier) {
        Box(
            modifier = roundedSquareLargeModifier.aspectRatio(1f)
        ) {
            LazyVerticalGrid(
                modifier = Modifier
                    .align(Alignment.Center),
                columns = GridCells.Fixed(3),
            ) {
                items(itemList.size) {
                    RoundedSquareImageItem(
                        modifier = roundedSquareMediumModifier,
                        imageUri = itemList[it].fittingThumbnailImg.toUri(),
                        icon = null,
                        onClick = { },
                    )
                }
            }
        }
    }

}