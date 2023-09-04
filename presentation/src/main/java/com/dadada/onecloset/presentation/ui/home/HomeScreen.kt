package com.dadada.onecloset.presentation.ui.home

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.dadada.onecloset.presentation.R
import com.dadada.onecloset.presentation.ui.CameraNav
import com.dadada.onecloset.presentation.ui.common.AlertDialogWithTwoButton
import com.dadada.onecloset.presentation.ui.common.CardWithAnimation
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale

private const val TAG = "HomeScreen"

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun HomeScreen(navController: NavHostController) {
    val context = LocalContext.current
    val cameraPermissionState: PermissionState = rememberPermissionState(Manifest.permission.CAMERA)
    val openAlertDialog = remember { mutableStateOf(false) }
    val isPermissionRequested = remember { mutableStateOf(false) }

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
        navController.navigate(CameraNav.route)
    }

    Column(modifier = Modifier.fillMaxSize()) {
        CardWithAnimation(
            title = "Care",
            content = "의류 이미지로 세탁기, 건조기, 에어 드레서\n코스를 추천 받으세요!",
            animation = R.raw.animation_course
        ) {
            checkCameraPermission(navController, cameraPermissionState, isPermissionRequested, openAlertDialog)
        }

        Spacer(modifier = Modifier.height(24.dp))
        CardWithAnimation(
            title = "Fitting",
            content = "옷장 속 의류 사진으로 나만의 가상 코디를\n만들어보세요!",
            animation = R.raw.animation_fitting
        ) {

        }
    }
}

@OptIn(ExperimentalPermissionsApi::class)
fun checkCameraPermission(
    navController: NavHostController,
    cameraPermissionState: PermissionState,
    isPermissionRequested: MutableState<Boolean>,
    openAlertDialog: MutableState<Boolean>,
    route: String = ""
) {
    if (cameraPermissionState.status.isGranted) {
        navController.navigate(CameraNav.route)
    } else if (cameraPermissionState.status.shouldShowRationale) {
        openAlertDialog.value = true
        isPermissionRequested.value = true
    } else {
        cameraPermissionState.launchPermissionRequest()
        isPermissionRequested.value = true
    }
}