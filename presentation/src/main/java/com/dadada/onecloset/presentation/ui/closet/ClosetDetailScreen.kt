package com.dadada.onecloset.presentation.ui.closet

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.navigation.NavHostController
import com.dadada.onecloset.domain.model.Cloth
import com.dadada.onecloset.presentation.ui.CameraNav
import com.dadada.onecloset.presentation.ui.ClothNav
import com.dadada.onecloset.presentation.ui.GalleryNav
import com.dadada.onecloset.presentation.ui.component.AlertDialogWithTwoButton
import com.dadada.onecloset.presentation.ui.component.ClothItemView
import com.dadada.onecloset.presentation.ui.component.CustomTabRow
import com.dadada.onecloset.presentation.ui.component.SelectPhotoBottomSheet
import com.dadada.onecloset.presentation.ui.home.checkCameraPermission
import com.dadada.onecloset.presentation.ui.theme.PrimaryBlack
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ClosetDetailScreen(navHostController: NavHostController) {
    val context = LocalContext.current
    var selectedTabIndex = remember { mutableStateOf(0) }
    var showSelectPhotoBottomSheet by remember { mutableStateOf(false) }
    val cameraPermissionState: PermissionState = rememberPermissionState(Manifest.permission.CAMERA)
    val isPermissionRequested = remember { mutableStateOf(false) }
    val openAlertDialog = remember { mutableStateOf(false) }

    val tabs = listOf("전체", "상의", "하의", "외투", "한벌옷")
    val tabWidths = remember {
        val tabWidthStateList = mutableStateListOf<Dp>()
        repeat(tabs.size) {
            tabWidthStateList.add(0.dp)
        }
        tabWidthStateList
    }
    val list =
        listOf(Cloth(), Cloth(), Cloth(), Cloth(), Cloth(), Cloth(), Cloth(), Cloth(), Cloth())

    if (showSelectPhotoBottomSheet) {
        SelectPhotoBottomSheet(onClickCamera = {
            checkCameraPermission(
                navHostController,
                cameraPermissionState,
                isPermissionRequested,
                openAlertDialog
            )
        }, onClickGallery = {
            navHostController.navigate(GalleryNav.route)
        }) {
            showSelectPhotoBottomSheet = !showSelectPhotoBottomSheet
        }
    }

    if (openAlertDialog.value) {
        AlertDialogWithTwoButton(
            onDismissRequest = { openAlertDialog.value = false },
            onConfirmation = {
                openAlertDialog.value = false
                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                    data = Uri.fromParts("package", context.packageName, null)
                }
                context.startActivity(intent)
            },
            dialogTitle = "서비스 이용 알림",
            dialogText = "카메라에 대한 권한 사용을 거부하였습니다. 기능 사용을 원하실 경우 휴대폰 설정 > 애플리케이션 관리자에서 해당 앱의 권한을 허용해주세요.",
            icon = Icons.Default.Info
        )
    }

    if (isPermissionRequested.value && cameraPermissionState.status.isGranted) {
        isPermissionRequested.value = false
        navHostController.navigate(CameraNav.route)
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(26.dp))
                .background(Color.White)
                .padding(it)
        ) {
            CustomTabRow(
                modifier = Modifier,
                tabs = tabs,
                selectedTabIndex = selectedTabIndex,
                tabWidths = tabWidths
            )
            LazyVerticalGrid(
                modifier = Modifier.padding(horizontal = 4.dp),
                columns = GridCells.Fixed(3),
            ) {
                items(list.size) {
                    ClothItemView(
                        modifier = Modifier
                            .padding(4.dp)
                            .clip(RoundedCornerShape(20.dp)),
                        imageUri = list[it].thumbnailImg.toUri()
                    ) {
                        navHostController.navigate(ClothNav.route)
                    }
                }
            }

        }
    }
}