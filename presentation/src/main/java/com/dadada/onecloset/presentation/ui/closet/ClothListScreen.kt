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
import com.dadada.onecloset.presentation.ui.CameraNav
import com.dadada.onecloset.presentation.ui.GalleryNav
import com.dadada.onecloset.presentation.ui.closet.component.ClothTabGridView
import com.dadada.onecloset.presentation.ui.component.SelectPhotoBottomSheet
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

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .padding(top = 24.dp),
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.padding(bottom = 16.dp),
                contentColor = Color.White,
                containerColor = PrimaryBlack,
                onClick = { showSelectPhotoBottomSheet = !showSelectPhotoBottomSheet }
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "fab")
            }
        },
    ) {
        ClothTabGridView(paddingValues = it, navHostController = navHostController)
    }
}