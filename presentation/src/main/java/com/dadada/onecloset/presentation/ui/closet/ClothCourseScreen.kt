package com.dadada.onecloset.presentation.ui.closet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.navigation.NavHostController
import com.dadada.onecloset.presentation.ui.NavigationItem
import com.dadada.onecloset.presentation.ui.closet.component.SelectClosetBottomSheet
import com.dadada.onecloset.presentation.ui.components.RoundedSquare
import com.dadada.onecloset.presentation.ui.components.RoundedSquareImageItem
import com.dadada.onecloset.presentation.ui.components.RowWithTwoButtons
import com.dadada.onecloset.presentation.ui.components.roundedSquareLargeModifier
import com.dadada.onecloset.presentation.ui.components.screenModifier
import com.dadada.onecloset.presentation.ui.theme.BackGround
import com.dadada.onecloset.presentation.ui.theme.Paddings
import com.dadada.onecloset.presentation.ui.utils.NetworkResultHandler
import com.dadada.onecloset.presentation.ui.utils.ShowToast
import com.dadada.onecloset.presentation.viewmodel.MainViewModel
import com.dadada.onecloset.presentation.viewmodel.closet.ClosetViewModel
import kotlinx.coroutines.launch

private const val TAG = "ClothCourseScreen"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClothCourseScreen(
    navHostController: NavHostController,
    mainViewModel: MainViewModel,
    closetViewModel: ClosetViewModel
) {
    val list = listOf("세탁", "건조", "에어드레서")
    val contentList = listOf(
        closetViewModel.clothesInfo.laundry,
        closetViewModel.clothesInfo.dryer,
        closetViewModel.clothesInfo.airDressor
    )

    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showToast by remember { mutableStateOf(false) }
    if(showToast) {
        ShowToast(text = "의류가 저장됐어요!")
    }

    val clothRegisterIdState by closetViewModel.clothRegisterIdState.collectAsState()
    NetworkResultHandler(state = clothRegisterIdState, mainViewModel = mainViewModel) {
        closetViewModel.resetNetworkStates()
        showToast = true
        navHostController.navigate("${NavigationItem.ClothNav.route}/${it}") {
            popUpTo(NavigationItem.GalleryNav.route) { inclusive = true }
        }
    }

    if (sheetState.isVisible) {
        SelectClosetBottomSheet(
            sheetState = sheetState,
            mainViewModel = mainViewModel,
            onDismissRequest = { scope.launch { sheetState.hide() } },
            onClick = {
                closetViewModel.clothesInfo.closetId = it.toString()
                closetViewModel.putCloth()
            }
        )
    }


    Box(
        modifier = screenModifier,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 56.dp)
                .verticalScroll(rememberScrollState())
        ) {
            RoundedSquareImageItem(
                modifier = roundedSquareLargeModifier,
                imageUri = closetViewModel.clothesInfo.image.toUri(),
                icon = null,
            ) {

            }
            Spacer(modifier = Modifier.size(Paddings.xlarge))
            Spacer(modifier = Modifier.weight(1f))
            Column() {
                list.forEachIndexed { index, s ->
                    RoundedSquare(title = s, content = contentList[index])
                    Spacer(modifier = Modifier.size(12.dp))
                }
            }
            Spacer(modifier = Modifier.weight(1f))
        }
        RowWithTwoButtons(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .background(BackGround),
            left = "취소",
            right = "저장",
            onClickLeft = {
                closetViewModel.resetNetworkStates()
                navHostController.navigate(NavigationItem.MainTabNav.route) {
                    popUpTo(NavigationItem.MainTabNav.route) { inclusive = true }
                }
            }) {
            scope.launch { sheetState.show() }
        }
    }
}