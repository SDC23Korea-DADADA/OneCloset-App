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
import com.dadada.onecloset.presentation.R
import com.dadada.onecloset.presentation.ui.NavigationItem
import com.dadada.onecloset.presentation.ui.closet.component.ClothTabGridView
import com.dadada.onecloset.presentation.ui.common.RowWithTwoButtons
import com.dadada.onecloset.presentation.ui.common.SelectPhotoBottomSheet
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

    var clickedState by remember { mutableStateOf(listOf<Boolean>()) }
    var clickedTopState by remember { mutableStateOf(listOf<Boolean>()) }
    var clickedBottomState by remember { mutableStateOf(listOf<Boolean>()) }
    val emptyItemList: List<List<FittingEmptyItem>> = FittingEmptyItem.getList()
    var curEmptyItemList: List<FittingEmptyItem> by remember { mutableStateOf(emptyItemList[0]) }
    var modeIdx by remember { mutableStateOf(0) }

    var selectedItemList by remember {
        mutableStateOf(
            arrayListOf<ClothesInfo>(
                ClothesInfo(),
            )
        )
    }
    var selectedItemSize by remember { mutableStateOf(emptyItemList[modeIdx].size) }


    var handleItemClick = { newIndex: Int ->
        val newClickedState = clickedState.toMutableList()
        if (newClickedState[newIndex]) {
            newClickedState[newIndex] = false
            selectedItemList = replaceClothById(selectedItemList, clothList[newIndex].clothesId)
        } else if (!newClickedState[newIndex] && getCountById(selectedItemList) < selectedItemSize) {
            newClickedState[newIndex] = true
            selectedItemList = putClothById(selectedItemList, clothList[newIndex])
        }
        clickedState = newClickedState
    }


    val tabsList = listOf(listOf("상의"), listOf("하의"), listOf("한벌옷"))
    var tabsListState by remember {
        mutableStateOf(tabsList[modeIdx])
    }

    LaunchedEffect(key1 = modeIdx) {
        clothList = allClothList.filter { it.upperType == tabsList[modeIdx][0] }
        curEmptyItemList = emptyItemList[modeIdx]
        selectedItemSize = emptyItemList[modeIdx].size
        selectedItemList = ArrayList(List(selectedItemSize) { ClothesInfo() })
        clickedState = List(clothList.size) { false }
        tabsListState = tabsList[modeIdx]
    }

    LaunchedEffect(Unit) {
        closetViewModel.getBasicClothList()
    }

    NetworkResultHandler(state = clothListState, mainViewModel = mainViewModel) {
        allClothList = it
        clothList = allClothList.filter {
            it.upperType == tabsList[modeIdx][0]
        }
        clickedState = List(clothList.size) { false }
    }

    NetworkResultHandler(state = fittingResultState, loadingType = LoadingType.FITTING, mainViewModel = mainViewModel) {
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
                clothList = clothList,
                modeIdx = modeIdx,
                emptyItemList = curEmptyItemList,
                selectedItemList = selectedItemList,
                onClickDropDown = { modeIdx = it }
            )
            Spacer(modifier = Modifier.size(12.dp))

            ClothTabGridView(
                modifier = Modifier
                    .align(alignment = Alignment.CenterHorizontally)
                    .padding(bottom = 56.dp),
                navHostController = navHostController,
                clothItems = clothList,
                icon = R.drawable.ic_checked,
                itemClickedStateList = clickedState,
                onClick = handleItemClick,
                onClickTab = { upperType ->
                    clothList = allClothList.filter { it.upperType == upperType }
                },
                tabs = tabsListState
            )
        }

        RowWithTwoButtons(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .background(BackGround),
            left = "취소",
            right = "다음",
            onClickLeft = { navHostController.popBackStack() },
            onClickRight = {
                val selectedItem = arrayListOf<Int>()
                when (modeIdx) {
//                    0 -> {
//                        fittingViewModel.setFittingInfoTopId(selectedItemList[0].clothesId.toString())
//                        fittingViewModel.setFittingInfoBottomId(selectedItemList[1].clothesId.toString())
//                    }

                    0 -> {
                        fittingViewModel.setFittingInfoTopId(selectedItemList[0].clothesId.toString())
                    }

                    1 -> {
                        fittingViewModel.setFittingInfoBottomId(selectedItemList[0].clothesId.toString())
                    }

                    else -> {
                        fittingViewModel.setFittingInfoOneId(selectedItemList[0].clothesId.toString())
                    }
                }
                selectedItemList.forEach {
                    selectedItem.add(it.clothesId)
                }
                fittingViewModel.fittingResultForSave.clothesIdList = selectedItem
                fittingViewModel.getFittingResult()
            }
        )
    }
}

fun replaceClothById(
    selectedItemList: ArrayList<ClothesInfo>,
    targetId: Int
): ArrayList<ClothesInfo> {
    val newList = ArrayList(selectedItemList)
    val index = newList.indexOfFirst { it.clothesId == targetId }
    if (index != -1) {
        newList[index] = ClothesInfo() // Replace the item at the specified index
    }
    return newList
}

fun putClothById(
    selectedItemList: ArrayList<ClothesInfo>,
    newCloth: ClothesInfo
): ArrayList<ClothesInfo> {
    val newList = ArrayList(selectedItemList)
    val index = newList.indexOfFirst { it.clothesId == -1 }
    if (index != -1) {
        newList[index] = newCloth // Replace the item at the specified index
    }
    return newList
}


fun getCountById(selectedItemList: ArrayList<ClothesInfo>): Int {
    return selectedItemList.count { it.clothesId != -1 }
}

