package com.dadada.onecloset.presentation.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.dadada.onecloset.presentation.R
import com.dadada.onecloset.presentation.ui.CameraNav
import com.dadada.onecloset.presentation.ui.FittingNav
import com.dadada.onecloset.presentation.ui.GalleryNav
import com.dadada.onecloset.presentation.ui.common.LargeRoundedSquareWithAnimation
import com.dadada.onecloset.presentation.ui.common.SelectPhotoBottomSheet
import com.dadada.onecloset.presentation.ui.utils.PermissionRequester
import com.dadada.onecloset.presentation.ui.utils.Permissions
import com.google.accompanist.permissions.ExperimentalPermissionsApi

private const val TAG = "HomeScreen"

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun HomeScreen(navHostController: NavHostController) {
    var clickCourse by remember { mutableStateOf(false) }

    if(clickCourse) {
        PermissionRequester(
            permission = Permissions.readExternalStoragePermission,
            onDismissRequest = { clickCourse = !clickCourse },
            onPermissionGranted = { navHostController.navigate(GalleryNav.route) }) {
            clickCourse = !clickCourse
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 24.dp)
    ) {
        LargeRoundedSquareWithAnimation(
            title = "Care",
            content = "의류 이미지로 세탁기, 건조기, 에어 드레서\n코스를 추천 받으세요!",
            animation = R.raw.animation_course
        ) {
            clickCourse = !clickCourse
        }

        Spacer(modifier = Modifier.height(24.dp))
        LargeRoundedSquareWithAnimation(
            title = "Fitting",
            content = "옷장 속 의류 사진으로 나만의 가상 코디를\n만들어보세요!",
            animation = R.raw.animation_fitting
        ) {
            navHostController.navigate(FittingNav.route)
        }
    }
}
