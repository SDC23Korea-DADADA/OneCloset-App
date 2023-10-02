package com.dadada.onecloset.presentation.ui.coordi.component.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.core.net.toUri
import androidx.navigation.NavHostController
import com.dadada.onecloset.domain.model.codi.Codi
import com.dadada.onecloset.presentation.ui.NavigationItem
import com.dadada.onecloset.presentation.ui.components.RoundedSquareImageItem
import com.dadada.onecloset.presentation.ui.theme.roundedSquareLargeModifier
import com.dadada.onecloset.presentation.viewmodel.codi.CodiViewModel

@Composable
fun CoordiCodiListView(
    navHostController: NavHostController,
    itemList: List<Codi>,
    codiViewModel: CodiViewModel,
) {
    Column(modifier = roundedSquareLargeModifier) {
        Box(
            modifier = roundedSquareLargeModifier.fillMaxSize(),
        ) {
            LazyVerticalGrid(
                modifier = Modifier
                    .align(Alignment.Center),
                columns = GridCells.Fixed(3),
            ) {
                items(itemList.size) {
                    RoundedSquareImageItem(
                        imageUri = itemList[it].thumbnailImg.toUri(),
                        icon = null,
                        onClick = {
                            codiViewModel.curDailyCodiItem = itemList[it]
                            navHostController.navigate(NavigationItem.CoordinationDetailNav.route)
                        },
                    )
                }
            }
        }
    }
}
