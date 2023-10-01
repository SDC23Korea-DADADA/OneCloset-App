package com.dadada.onecloset.presentation.ui.fitting

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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.dadada.onecloset.domain.model.clothes.ClothesInfo
import com.dadada.onecloset.presentation.ui.NavigationItem
import com.dadada.onecloset.presentation.ui.closet.component.ClothTabGridView
import com.dadada.onecloset.presentation.ui.components.TwoButtonRow
import com.dadada.onecloset.presentation.ui.components.SelectPhotoBottomSheet
import com.dadada.onecloset.presentation.ui.fitting.component.FittingSelectedClothListView
import com.dadada.onecloset.presentation.ui.theme.BackGround
import com.dadada.onecloset.presentation.ui.utils.FittingEmptyItem
import com.dadada.onecloset.presentation.ui.utils.LoadingType
import com.dadada.onecloset.presentation.ui.utils.NetworkResultHandler
import com.dadada.onecloset.presentation.viewmodel.MainViewModel
import com.dadada.onecloset.presentation.viewmodel.closet.ClosetViewModel
import com.dadada.onecloset.presentation.viewmodel.fitting.FittingViewModel

private const val TAG = "FittingScreen"

@Composable
fun FittingScreen(
    navHostController: NavHostController,
    mainViewModel: MainViewModel,
    fittingViewModel: FittingViewModel,
    closetViewModel: ClosetViewModel = hiltViewModel()
) {
    val fittingResultState by fittingViewModel.fittingResultState.collectAsState()
    val clothListState by closetViewModel.clothListState.collectAsState()
    var clothList by remember { mutableStateOf(listOf<ClothesInfo>()) }
    var allClothList by remember { mutableStateOf(listOf<ClothesInfo>()) }


    var modeIdx by remember { mutableStateOf(0) }

    val emptyItemList: List<List<FittingEmptyItem>> = FittingEmptyItem.getList()
    var curEmptyItemList = remember(modeIdx) { mutableStateOf(emptyItemList[modeIdx]) }

    val tabsList = listOf(listOf("상의"), listOf("하의"), listOf("한벌옷"), listOf("상의", "하의"))
    var tabsListState by remember { mutableStateOf(tabsList[modeIdx]) }

    var selectedItemOne by remember { mutableStateOf(-1) }
    var selectedItemTwo by remember { mutableStateOf(-1) }

    LaunchedEffect(key1 = modeIdx) {
        clothList = allClothList.filter { it.upperType == tabsList[modeIdx][0] }
        tabsListState = tabsList[modeIdx]
        selectedItemOne = -1
        selectedItemTwo = -1
    }

    LaunchedEffect(Unit) {
        closetViewModel.getBasicClothList()
    }

    NetworkResultHandler(state = clothListState, mainViewModel = mainViewModel) {
        allClothList = it
        clothList = allClothList.filter { it.upperType == tabsList[modeIdx][0] }
    }

    NetworkResultHandler(
        state = fittingResultState,
        loadingType = LoadingType.FITTING,
        mainViewModel = mainViewModel
    ) {
        fittingViewModel.fittingResult = it
        fittingViewModel.resetNetworkStates()
        navHostController.navigate(NavigationItem.FittingResultNav.route)
    }


    var showSelectPhotoBottomSheet by remember { mutableStateOf(false) }
    if (showSelectPhotoBottomSheet) {
        SelectPhotoBottomSheet(onClickCamera = {
            navHostController.navigate(NavigationItem.CameraNav.route)
        }, onClickGallery = {
            navHostController.navigate(NavigationItem.GalleryNav.route)
        }) {
            showSelectPhotoBottomSheet = !showSelectPhotoBottomSheet
        }
    }
    Box(modifier = Modifier.padding(16.dp)) {
        Column() {
            FittingSelectedClothListView(
                clothList = allClothList,
                modeIdx = modeIdx,
                emptyItemList = curEmptyItemList,
                selectedItemList = if (modeIdx == 3) listOf(
                    selectedItemOne,
                    selectedItemTwo
                ) else listOf(selectedItemOne)
            ) { modeIdx = it }
            Spacer(modifier = Modifier.size(12.dp))

            ClothTabGridView(
                modifier = Modifier
                    .align(alignment = Alignment.CenterHorizontally)
                    .padding(bottom = 56.dp),
                navHostController = navHostController,
                clothItems = clothList,
                isSearch = false,
                onClick = { newId ->
                    val cloth = allClothList.find { it.clothesId == newId }
                    when (modeIdx) {
                        3 -> {
                            if (cloth != null) {
                                if (cloth.upperType == "상의") {
                                    selectedItemOne = newId
                                } else {
                                    selectedItemTwo = newId
                                }
                            }
                        }

                        else -> {
                            selectedItemOne = newId
                        }
                    }
                },
                onClickTab = { upperType ->
                    clothList = allClothList.filter { it.upperType == upperType }
                },
                tabs = tabsList[modeIdx]
            )
        }

        TwoButtonRow(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .background(BackGround),
            left = "취소",
            right = "다음",
            onClickLeft = { navHostController.popBackStack() },
            onClickRight = {
                val curSelectItem = listOf<Int>(selectedItemOne, selectedItemTwo).filter { it != -1 }
                when (modeIdx) {
//                    0 -> {
//                        fittingViewModel.setFittingInfoTopId(selectedItemList[0].clothesId.toString())
//                        fittingViewModel.setFittingInfoBottomId(selectedItemList[1].clothesId.toString())
//                    }

                    0 -> {
                        fittingViewModel.setFittingInfoTopId(selectedItemOne.toString())
                    }

                    1 -> {
                        fittingViewModel.setFittingInfoBottomId(selectedItemOne.toString())
                    }

                    2 -> {
                        fittingViewModel.setFittingInfoOneId(selectedItemOne.toString())
                    }
                    else -> {
                        fittingViewModel.setFittingInfoTopId(selectedItemOne.toString())
                        fittingViewModel.setFittingInfoBottomId(selectedItemTwo.toString())
                    }
                }
                fittingViewModel.fittingResultForSave.clothesIdList = curSelectItem
                fittingViewModel.getFittingResult()
            }
        )
    }
}
