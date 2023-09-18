package com.dadada.onecloset.presentation.ui.closet

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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.dadada.onecloset.domain.model.Cloth
import com.dadada.onecloset.presentation.ui.NavigationItem
import com.dadada.onecloset.presentation.ui.closet.component.ClothTabGridView
import com.dadada.onecloset.presentation.ui.common.CustomFloatingActionButton
import com.dadada.onecloset.presentation.ui.common.SelectPhotoBottomSheet
import com.dadada.onecloset.presentation.ui.utils.NetworkResultHandler
import com.dadada.onecloset.presentation.viewmodel.closet.ClosetViewModel

@Composable
fun ClothListScreen(navHostController: NavHostController, closetViewModel: ClosetViewModel) {
    val clothListState by closetViewModel.clothListState.collectAsState()
    var clothList by remember { mutableStateOf(listOf<Cloth>()) }
    LaunchedEffect(Unit) {
        closetViewModel.getClothList()
    }

    NetworkResultHandler(state = clothListState) {
        clothList = it
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
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .padding(top = 24.dp),
        floatingActionButton = {
            CustomFloatingActionButton(icon = Icons.Default.Add) {
                navHostController.navigate(NavigationItem.GalleryNav.route)
            }
        },
    ) {
        ClothTabGridView(
            paddingValues = it,
            navHostController = navHostController,
            clothItems = clothList,
            onClick = { navHostController.navigate(NavigationItem.ClothNav.route) }
        )
    }
}