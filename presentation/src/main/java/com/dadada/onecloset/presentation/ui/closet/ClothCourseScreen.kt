package com.dadada.onecloset.presentation.ui.closet

import android.util.Log
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.navigation.NavHostController
import com.dadada.onecloset.presentation.ui.NavigationItem
import com.dadada.onecloset.presentation.ui.closet.component.SelectClosetBottomSheet
import com.dadada.onecloset.presentation.ui.common.RoundedSquare
import com.dadada.onecloset.presentation.ui.common.RoundedSquareImageItem
import com.dadada.onecloset.presentation.ui.common.RowWithTwoButtons
import com.dadada.onecloset.presentation.ui.common.roundedSquareLargeModifier
import com.dadada.onecloset.presentation.ui.common.screenModifier
import com.dadada.onecloset.presentation.ui.theme.BackGround
import com.dadada.onecloset.presentation.ui.theme.Paddings
import com.dadada.onecloset.presentation.ui.utils.NetworkResultHandler
import com.dadada.onecloset.presentation.viewmodel.closet.ClosetViewModel
import kotlinx.coroutines.launch

private const val TAG = "ClothCourseScreen"
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClothCourseScreen(navHostController: NavHostController, closetViewModel: ClosetViewModel) {
    val list = listOf("세탁", "건조", "에어드레서")
    val contentList = listOf(
        "울 소재의 옷은 울 전용 세제를 넣어 울 전용 코스를 이용해보세요!",
        "울 소재의 옷은 울 전용 세제를 넣어 울 전용 코스를 이용해보세요!",
        "울 소재의 옷은 울 전용 세제를 넣어 울 전용 코스를 이용해보세요!"
    )

    closetViewModel.cloth.laundry = contentList[0]
    closetViewModel.cloth.dryer = contentList[1]
    closetViewModel.cloth.airDressor = contentList[2]

    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

    val clothRegisterIdState by closetViewModel.clothRegisterIdState.collectAsState()
    NetworkResultHandler(state = clothRegisterIdState) {
        navHostController.navigate("${NavigationItem.ClothNav.route}/${it}") {
            popUpTo(NavigationItem.GalleryNav.route) { inclusive = true }
        }
    }

    if (sheetState.isVisible) {
        SelectClosetBottomSheet(
            sheetState = sheetState,
            onDismissRequest = { scope.launch { sheetState.hide() } },
            onClick = {
                closetViewModel.cloth.closetId = it.toString()
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
                imageUri = closetViewModel.cloth.img.toUri(),
                icon = null,
            ) {

            }
            Spacer(modifier = Modifier.size(Paddings.xlarge))
            Spacer(modifier = Modifier.weight(1f))
            Column() {
                list.forEachIndexed { index, s ->
                    Log.d(TAG, "ClothCourseScreen: $contentList")
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
            right = "등록하기",
            onClickLeft = { /*TODO*/ }) {
                scope.launch { sheetState.show() }
        }
    }
}