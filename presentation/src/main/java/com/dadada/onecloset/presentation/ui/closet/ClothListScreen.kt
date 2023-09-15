package com.dadada.onecloset.presentation.ui.closet

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.dadada.onecloset.domain.model.Cloth
import com.dadada.onecloset.presentation.ui.CameraNav
import com.dadada.onecloset.presentation.ui.ClothNav
import com.dadada.onecloset.presentation.ui.GalleryNav
import com.dadada.onecloset.presentation.ui.closet.component.ClothTabGridView
import com.dadada.onecloset.presentation.ui.common.CustomFloatingActionButton
import com.dadada.onecloset.presentation.ui.common.SelectPhotoBottomSheet
import com.dadada.onecloset.presentation.ui.theme.PrimaryBlack

@Composable
fun ClothListScreen(navHostController: NavHostController) {
    var showSelectPhotoBottomSheet by remember { mutableStateOf(false) }
    if (showSelectPhotoBottomSheet) {
        SelectPhotoBottomSheet(onClickCamera = {
            navHostController.navigate(CameraNav.route)
        }, onClickGallery = {
            navHostController.navigate(GalleryNav.route)
        }) {
            showSelectPhotoBottomSheet = !showSelectPhotoBottomSheet
        }
    }

    val list = listOf<Cloth>(
        Cloth(), Cloth(), Cloth(), Cloth(), Cloth(), Cloth(), Cloth(), Cloth(),
    )
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .padding(top = 24.dp),
        floatingActionButton = {
            CustomFloatingActionButton(icon = Icons.Default.Add) {
                navHostController.navigate(GalleryNav.route)
            }
        },
    ) {
        ClothTabGridView(
            paddingValues = it,
            navHostController = navHostController,
            clothItems = list,
            onClick = { navHostController.navigate(ClothNav.route) }
        )
    }
}