package com.dadada.onecloset.presentation.ui.coordination

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.dadada.onecloset.presentation.ui.common.CustomTabRow
import com.dadada.onecloset.presentation.ui.common.roundedSquareLargeModifier
import com.dadada.onecloset.presentation.ui.common.screenModifier
import com.dadada.onecloset.presentation.ui.coordination.component.CodiResultView
import com.dadada.onecloset.presentation.ui.coordination.component.EmptyView
import com.dadada.onecloset.presentation.ui.theme.BackGroundGray
import com.dadada.onecloset.presentation.ui.theme.Paddings
import com.dadada.onecloset.presentation.ui.theme.TextGray
import com.dadada.onecloset.presentation.ui.theme.Typography
import com.dadada.onecloset.presentation.viewmodel.codi.CodiViewModel

@Composable
fun CoordinationResultScreen(codiViewModel: CodiViewModel, navController: NavHostController) {
    var selectedTabIndex by remember { mutableStateOf(0) }
    val handleTabClick = { newIndex: Int ->
        selectedTabIndex = newIndex
    }

    val tabs = listOf("데일리 코디", "계획")
    val tabWidths = remember {
        val tabWidthStateList = mutableStateListOf<Dp>()
        repeat(tabs.size) {
            tabWidthStateList.add(0.dp)
        }
        tabWidthStateList
    }


    Column(
        modifier = screenModifier
    ) {
        CustomTabRow(
            modifier = Modifier,
            tabs = tabs,
            selectedTabIndex = selectedTabIndex,
            tabWidths = tabWidths,
            tabClick = handleTabClick
        )


        when (selectedTabIndex) {

            0 -> {
                if (codiViewModel.curDailyCodiItem.originImg == "") {
                    EmptyView(title = "등록된 데일리 코디가 없어요.\n오늘의 코디를 기록해보는 건 어떨까요?")
                } else {
                    CodiResultView(
                        imagePath = codiViewModel.curDailyCodiItem.originImg,
                        clothesList = codiViewModel.curDailyCodiItem.clothesList,
                        navController = navController,
                        codiViewModel = codiViewModel
                    )
                }
            }

            else -> codiViewModel.curFittingItem.fittingImg.let {
                if(codiViewModel.curFittingItem.originImg == "") {
                    EmptyView(title = "등록된 코디 계획이 없어요.\n이 날의 코디를 계획해보는 건 어떨까요?")
                } else {
                    CodiResultView(
                        imagePath = it,
                        clothesList = codiViewModel.curFittingItem.clothesList,
                        navController = navController,
                        codiViewModel = codiViewModel
                    )
                }
            }
        }
    }
}

@Composable
fun ImageView(url: String) {
    Box(modifier = roundedSquareLargeModifier) {
        AsyncImage(
            modifier = Modifier
                .fillMaxSize(),
            model = url,
            contentDescription = "",
            contentScale = ContentScale.Crop
        )
    }
}