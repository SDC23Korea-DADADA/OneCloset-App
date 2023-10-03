package com.dadada.onecloset.presentation.ui.coordi

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.dadada.onecloset.presentation.ui.common.card.TipCard
import com.dadada.onecloset.presentation.ui.common.row.CustomTabRow
import com.dadada.onecloset.presentation.ui.coordi.component.view.CoordiResultView
import com.dadada.onecloset.presentation.ui.theme.screenModifier
import com.dadada.onecloset.presentation.viewmodel.codi.CodiViewModel

@Composable
fun CoordiDetailCalendarScreen(codiViewModel: CodiViewModel, navController: NavHostController) {
    var selectedTabIndex by remember { mutableIntStateOf(0) }
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
        modifier = screenModifier,
    ) {
        CustomTabRow(
            modifier = Modifier,
            tabs = tabs,
            selectedTabIndex = selectedTabIndex,
            tabWidths = tabWidths,
            tabClick = handleTabClick,
        )

        when (selectedTabIndex) {
            0 -> {
                if (codiViewModel.curDailyCodiItem.originImg == "") {
                    Spacer(modifier = Modifier.weight(1f))
                    TipCard(
                        content = "등록된 데일리 코디가 없어요.\n오늘의 코디를 기록해보는 건 어떨까요?",
                        isClickable = false,
                    ) {
                    }
                    Spacer(modifier = Modifier.weight(1f))
                } else {
                    CoordiResultView(
                        imagePath = codiViewModel.curDailyCodiItem.originImg,
                        clothesList = codiViewModel.curDailyCodiItem.clothesList,
                        navController = navController,
                    )
                }
            }

            else -> codiViewModel.curFittingItem.fittingImg.let {
                if (codiViewModel.curFittingItem.originImg == "") {
                    Spacer(modifier = Modifier.weight(1f))
                    TipCard(
                        content = "등록된 코디 계획이 없어요.\n이 날의 코디를 계획해보는 건 어떨까요?",
                        isClickable = false,
                    ) {
                    }
                    Spacer(modifier = Modifier.weight(1f))
                } else {
                    CoordiResultView(
                        imagePath = it,
                        clothesList = codiViewModel.curFittingItem.clothesList,
                        navController = navController,
                    )
                }
            }
        }
    }
}
