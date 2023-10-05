package com.dadada.onecloset.presentation.ui.coordi

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.dadada.onecloset.domain.model.clothes.ClothesInfo
import com.dadada.onecloset.presentation.R
import com.dadada.onecloset.presentation.ui.NavigationItem
import com.dadada.onecloset.presentation.ui.clothes.component.view.ClothesListTabGridView
import com.dadada.onecloset.presentation.ui.common.item.BasicImageItem
import com.dadada.onecloset.presentation.ui.common.row.TwoButtonRow
import com.dadada.onecloset.presentation.ui.theme.BackGround
import com.dadada.onecloset.presentation.ui.theme.screenModifier
import com.dadada.onecloset.presentation.ui.utils.NetworkResultHandler
import com.dadada.onecloset.presentation.viewmodel.MainViewModel
import com.dadada.onecloset.presentation.viewmodel.closet.ClosetViewModel
import com.dadada.onecloset.presentation.viewmodel.codi.CodiViewModel

@Composable
fun CoordiRegisterScreen(
    navHostController: NavHostController,
    mainViewModel: MainViewModel,
    codiViewModel: CodiViewModel,
    closetViewModel: ClosetViewModel = hiltViewModel(),
) {
    val clothListState by closetViewModel.clothListState.collectAsState()
    var clothList by remember { mutableStateOf(listOf<ClothesInfo>()) }
    var allClothList by remember { mutableStateOf(listOf<ClothesInfo>()) }

    var clickedState by remember { mutableStateOf(listOf<Boolean>()) }

    val handleItemClick = { clothId: Int ->
        val index = clothList.indexOfFirst { it.clothesId == clothId }
        if (index != -1) {
            val newClickedState = clickedState.toMutableList()
            newClickedState[index] = !newClickedState[index]
            clickedState = newClickedState
        }
    }

    val codiPutState by codiViewModel.codiPutState.collectAsState()
    NetworkResultHandler(state = codiPutState, mainViewModel = mainViewModel) {
        codiViewModel.curDailyCodiItem.id = it
        navHostController.navigate(NavigationItem.CoordinationNav.route) {
            popUpTo(NavigationItem.GalleryNav.route) { inclusive = true }
        }
    }

    LaunchedEffect(Unit) {
        closetViewModel.getBasicClothList()
    }

    NetworkResultHandler(state = clothListState, mainViewModel = mainViewModel) {
        allClothList = it
        clothList = it
        clickedState = List(clothList.size) { false }
    }

    Box(modifier = screenModifier) {
        Column {
            BasicImageItem(
                imageUri = codiViewModel.codiRegisterInfo.imagePath.toUri(),
                icon = null,
            ) {
            }
            Spacer(modifier = Modifier.size(12.dp))
            ClothesListTabGridView(
                modifier = Modifier
                    .align(alignment = Alignment.CenterHorizontally)
                    .padding(bottom = 56.dp),
                clothItems = clothList,
                icon = R.drawable.ic_checked,
                itemClickedStateList = clickedState,
                onClick = handleItemClick,
                onClickTab = { upperType ->
                    clothList =
                        if (upperType == "전체") allClothList else allClothList.filter { it.upperType == upperType }
                },
                tabs = listOf("전체", "상의", "하의", "외투", "한벌옷"),
            )
        }

        TwoButtonRow(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .background(BackGround),
            left = "취소",
            right = "등록",
            onClickLeft = { navHostController.popBackStack() },
            onClickRight = {
                codiViewModel.codiRegisterInfo.clothesList =
                    allClothList.filterIndexed { index, _ -> clickedState[index] }
                        .map { it.clothesId }
                codiViewModel.putCodi()
            },
        )
    }
}
