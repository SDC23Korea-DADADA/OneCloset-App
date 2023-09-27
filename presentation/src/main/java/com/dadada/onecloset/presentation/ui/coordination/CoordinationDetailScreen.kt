package com.dadada.onecloset.presentation.ui.coordination

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.dadada.onecloset.presentation.ui.NavigationItem
import com.dadada.onecloset.presentation.ui.closet.component.ClothHeader
import com.dadada.onecloset.presentation.ui.common.CustomTabRow
import com.dadada.onecloset.presentation.ui.common.RoundedSquareImageItem
import com.dadada.onecloset.presentation.ui.common.roundedSquareMediumModifier
import com.dadada.onecloset.presentation.ui.common.screenModifier
import com.dadada.onecloset.presentation.ui.coordination.component.CodiResultView
import com.dadada.onecloset.presentation.ui.theme.Paddings
import com.dadada.onecloset.presentation.ui.utils.NetworkResultHandler
import com.dadada.onecloset.presentation.viewmodel.codi.CodiViewModel
import com.dadada.onecloset.presentation.viewmodel.fitting.FittingViewModel

@Composable
fun CoordinationDetailScreen(codiViewModel: CodiViewModel, navController: NavHostController) {
    val deleteState by codiViewModel.codiDeleteState.collectAsState()
    NetworkResultHandler(state = deleteState) {
        navController.popBackStack()
    }
    Scaffold(
        topBar = {
            ClothHeader(
                navController = navController,
                isEdit = false,
                onClickEdit = {  },
                onClickDelete = { codiViewModel.deleteCodi() }
            )
        }
    ) {

        Column(modifier = screenModifier.padding(it)) {
            CodiResultView(
                imagePath = codiViewModel.curDailyCodiItem.originImg,
                clothesList = codiViewModel.curDailyCodiItem.clothesList,
                navController = navController,
                codiViewModel = codiViewModel
            )
        }
    }
}

@Composable
fun CoordinationFittingDetailScreen(
    codiViewModel: CodiViewModel,
    navController: NavHostController,
    fittingViewModel: FittingViewModel = hiltViewModel()
) {
    val deleteState by fittingViewModel.fittingDeleteState.collectAsState()
    NetworkResultHandler(state = deleteState) {
        navController.popBackStack()
    }
    var selectedTabIndex by remember { mutableStateOf(0) }
    val handleTabClick = { newIndex: Int ->
        selectedTabIndex = newIndex
    }

    val tabs = listOf("원본 사진", "가상 피팅")
    val tabWidths = remember {
        val tabWidthStateList = mutableStateListOf<Dp>()
        repeat(tabs.size) {
            tabWidthStateList.add(0.dp)
        }
        tabWidthStateList
    }


    Scaffold(
        topBar = {
            ClothHeader(
                navController = navController,
                isEdit = false,
                onClickEdit = {  },
                onClickDelete = { fittingViewModel.deleteFitting(codiViewModel.curFittingItem.id.toString()) }
            )
        }
    ) {
        it
        Column(
            modifier = screenModifier.padding(it)
        ) {
            Column {
                CustomTabRow(
                    modifier = Modifier,
                    tabs = tabs,
                    selectedTabIndex = selectedTabIndex,
                    tabWidths = tabWidths,
                    tabClick = handleTabClick
                )

                when (selectedTabIndex) {
                    0 -> RoundedSquareImageItem(
                        imageUri = codiViewModel.curFittingItem.originImg.toUri(),
                        icon = null
                    ) {}


                    else -> RoundedSquareImageItem(
                        imageUri = codiViewModel.curFittingItem.fittingImg.toUri(),
                        icon = null
                    ) {}
                }
            }

            Spacer(modifier = Modifier.size(Paddings.large))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(26.dp))
                    .background(Color.White)
                    .padding(Paddings.large)
            ) {
                LazyVerticalGrid(
                    modifier = Modifier.padding(horizontal = 4.dp),
                    columns = GridCells.Fixed(3)
                ) {
                    items(codiViewModel.curFittingItem.clothesList.size) {
                        RoundedSquareImageItem(
                            modifier = roundedSquareMediumModifier,
                            imageUri = codiViewModel.curFittingItem.clothesList[it].thumbnailImg.toUri(),
                            icon = null
                        ) {
                            navController.navigate("${NavigationItem.ClothNav.route}/${codiViewModel.curFittingItem.clothesList[it].clothesId}")
                        }
                    }
                }
            }
        }
    }
}