package com.dadada.onecloset.presentation.ui.closet

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.dadada.onecloset.domain.model.clothes.ClothesInfo
import com.dadada.onecloset.presentation.ui.NavigationItem
import com.dadada.onecloset.presentation.ui.closet.component.BasicHeader
import com.dadada.onecloset.presentation.ui.closet.component.ClothHeader
import com.dadada.onecloset.presentation.ui.closet.component.ClothTabGridView
import com.dadada.onecloset.presentation.ui.components.CustomFloatingActionButton
import com.dadada.onecloset.presentation.ui.components.SelectPhotoBottomSheet
import com.dadada.onecloset.presentation.ui.components.screenModifier
import com.dadada.onecloset.presentation.ui.utils.NetworkResultHandler
import com.dadada.onecloset.presentation.ui.utils.PermissionRequester
import com.dadada.onecloset.presentation.ui.utils.Permissions
import com.dadada.onecloset.presentation.ui.utils.ShowToast
import com.dadada.onecloset.presentation.viewmodel.MainViewModel
import com.dadada.onecloset.presentation.viewmodel.closet.ClosetViewModel

private const val TAG = "ClothListScreen"

@Composable
fun ClothListScreen(
    navHostController: NavHostController,
    mainViewModel: MainViewModel,
    closetViewModel: ClosetViewModel
) {
    val clothListState by closetViewModel.clothListState.collectAsState()
    var clothList by remember { mutableStateOf(listOf<ClothesInfo>()) }
    var allClothList by remember { mutableStateOf(listOf<ClothesInfo>()) }

    var clickCourse by remember { mutableStateOf(false) }
    if (clickCourse) {
        PermissionRequester(
            permission = Permissions.readExternalStoragePermission,
            onDismissRequest = { clickCourse = !clickCourse },
            onPermissionGranted = {
                navHostController.navigate(NavigationItem.GalleryNav.route)
            }) {
            clickCourse = !clickCourse
        }
    }

    LaunchedEffect(Unit) {
        closetViewModel.getClothList()
    }

    NetworkResultHandler(state = clothListState, mainViewModel = mainViewModel) {
        clothList = it
        allClothList = it
    }
    var showToast by remember {
        mutableStateOf(false)
    }
    if(showToast) {
        ShowToast(text = "옷장이 삭제됐어요.")
    }
    val closetDeleteState by closetViewModel.closetDeleteState.collectAsState()
    NetworkResultHandler(state = closetDeleteState, mainViewModel = mainViewModel) {
        showToast = true
        closetViewModel.resetNetworkStates()
        navHostController.popBackStack()
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

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        floatingActionButton = {
            CustomFloatingActionButton(title = "의류", icon = Icons.Default.Add) {
                clickCourse = !clickCourse
            }
        },
        topBar = {
            if(!closetViewModel.isBasicCloset) {
                ClothHeader(navController = navHostController, isEdit = false, onClickEdit = { /*TODO*/ }) {
                    closetViewModel.deleteCloset()
                }
            } else {
                BasicHeader(navController = navHostController)
            }
        }
    ) {
        Column(modifier = screenModifier.padding(it)) {
            ClothTabGridView(
                navHostController = navHostController,
                clothItems = clothList,
                onClick = {
                    navHostController.navigate("${NavigationItem.ClothNav.route}/${it}")
                },
                onClickTab = { upperType ->
                    clothList = if (upperType == "전체") {
                        allClothList
                    } else {
                        allClothList.filter { it.upperType == upperType }
                    }
                }
            )
        }
    }
}