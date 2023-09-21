package com.dadada.onecloset.presentation.ui.fitting

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.dadada.onecloset.presentation.ui.common.CustomTabRow
import com.dadada.onecloset.presentation.ui.common.screenModifier
import com.dadada.onecloset.presentation.ui.theme.Paddings
import com.dadada.onecloset.presentation.viewmodel.fitting.FittingViewModel

@Composable
fun FittingResultScreen(navHostController: NavHostController, fittingViewModel: FittingViewModel) {
    var selectedTabIndex by remember { mutableStateOf(0) }
    val handleTabClick = { newIndex: Int ->
        selectedTabIndex = newIndex
    }

    val tabs = listOf("원본 사진", "가상 피팅")
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

        when(selectedTabIndex) {
            0 -> AsyncImage(modifier = Modifier.fillMaxWidth().aspectRatio(1f).padding(Paddings.medium), model = fittingViewModel.fittingResult.originImg, contentDescription = "")
            else -> AsyncImage(modifier = Modifier.fillMaxWidth().aspectRatio(1f).padding(Paddings.medium), model = fittingViewModel.fittingResult.fittingImg, contentDescription = "")
        }

    }
}