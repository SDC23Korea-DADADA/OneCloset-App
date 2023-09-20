package com.dadada.onecloset.presentation.ui.fitting

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.dadada.onecloset.domain.model.Cloth
import com.dadada.onecloset.presentation.R
import com.dadada.onecloset.presentation.ui.NavigationItem
import com.dadada.onecloset.presentation.ui.closet.component.ClothTabGridView
import com.dadada.onecloset.presentation.ui.common.RowWithTwoButtons
import com.dadada.onecloset.presentation.ui.common.SelectPhotoBottomSheet
import com.dadada.onecloset.presentation.ui.fitting.component.EmptyClothItem
import com.dadada.onecloset.presentation.ui.fitting.component.FittingSelectedClothListView
import com.dadada.onecloset.presentation.ui.theme.BackGround
import com.dadada.onecloset.presentation.ui.utils.FittingEmptyItem
import com.dadada.onecloset.presentation.ui.utils.NetworkResultHandler
import com.dadada.onecloset.presentation.viewmodel.closet.ClosetViewModel

private const val TAG = "FittingScreen"

@Composable
fun FittingScreen(
    navHostController: NavHostController,
    closetViewModel: ClosetViewModel = hiltViewModel()
) {
    val clothListState by closetViewModel.clothListState.collectAsState()
    var clothList by remember { mutableStateOf(listOf<Cloth>()) }
    var clickedState by remember { mutableStateOf(listOf<Boolean>()) }
    val emptyItemList: List<List<FittingEmptyItem>> = FittingEmptyItem.getList()
    var modeIdx by remember { mutableStateOf(0) }
    var selectedItemList by remember { mutableStateOf(arrayListOf<Cloth>(Cloth(), Cloth())) }
    var selectedItemSize by remember { mutableStateOf(emptyItemList[modeIdx].size) }


    var handleItemClick = { newIndex: Int ->
        val newClickedState = clickedState.toMutableList()
        if(newClickedState[newIndex]) {
            newClickedState[newIndex] = false
            selectedItemList = replaceClothById(selectedItemList, clothList[newIndex].clothesId)
        } else if(!newClickedState[newIndex] && getCountById(selectedItemList) < selectedItemSize) {
            newClickedState[newIndex] = true
            selectedItemList = putClothById(selectedItemList, clothList[newIndex])
        }
        clickedState = newClickedState
    }

    LaunchedEffect(key1 = modeIdx) {
        selectedItemSize = emptyItemList[modeIdx].size
        selectedItemList = ArrayList(List(selectedItemSize) { Cloth() })
        clickedState = List(clothList.size) { false }
    }

    LaunchedEffect(Unit) {
        closetViewModel.getBasicClothList()
    }

    NetworkResultHandler(state = clothListState) {
        clothList = it
        clickedState = List(clothList.size) { false }
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
                emptyItemList = emptyItemList ,
                selectedItemList = selectedItemList,
                onClickDropDown = { modeIdx = it }
            )
            Spacer(modifier = Modifier.size(12.dp))
            ClothTabGridView(
                modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
                navHostController = navHostController,
                clothItems = clothList,
                icon = R.drawable.ic_checked,
                itemClickedStateList = clickedState,
                onClick = handleItemClick
            )
        }

        RowWithTwoButtons(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .background(BackGround),
            left = "취소",
            right = "다음",
            onClickLeft = { },
            onClickRight = { navHostController.navigate(NavigationItem.FittingResultNav.route) }
        )
    }
}

fun replaceClothById(selectedItemList: ArrayList<Cloth>, targetId: Int): ArrayList<Cloth> {
    val newList = ArrayList(selectedItemList)
    val index = newList.indexOfFirst { it.clothesId == targetId }
    if (index != -1) {
        newList[index] = Cloth() // Replace the item at the specified index
    }
    return newList
}

fun putClothById(selectedItemList: ArrayList<Cloth>, newCloth: Cloth): ArrayList<Cloth> {
    val newList = ArrayList(selectedItemList)
    val index = newList.indexOfFirst { it.clothesId == -1 }
    if (index != -1) {
        newList[index] = newCloth // Replace the item at the specified index
    }
    return newList
}


fun getCountById(selectedItemList: ArrayList<Cloth>): Int {
    return selectedItemList.count { it.clothesId != -1 }
}

