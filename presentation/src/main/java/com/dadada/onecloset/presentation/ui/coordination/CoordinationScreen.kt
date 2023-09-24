package com.dadada.onecloset.presentation.ui.coordination

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.dadada.onecloset.domain.model.codi.CodiList
import com.dadada.onecloset.presentation.ui.common.CustomTabRow
import com.dadada.onecloset.presentation.ui.common.screenModifier
import com.dadada.onecloset.presentation.ui.utils.NetworkResultHandler
import com.dadada.onecloset.presentation.viewmodel.PhotoViewModel
import com.dadada.onecloset.presentation.viewmodel.codi.CodiViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoordinationScreen(
    navHostController: NavHostController,
    codiViewModel: CodiViewModel,
    photoViewModel: PhotoViewModel
) {
    val codiListState by codiViewModel.codiListByMonth.collectAsState()
    var codiList by remember {
        mutableStateOf(CodiList(listOf(), listOf()))
    }
    NetworkResultHandler(state = codiListState) {
        codiList = it
    }


    LaunchedEffect(Unit) {
        codiViewModel.getCodiList()
    }

    var selectedTabIndex by remember { mutableStateOf(0) }
    val handleTabClick = { newIndex: Int ->
        selectedTabIndex = newIndex
    }

    val tabs = listOf("데일리 코디", "전체 코디", "가상 피팅")
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
            tabClick = handleTabClick
        )


        when (selectedTabIndex) {
            0 -> CoordinationCalendarScreen(navHostController = navHostController, codiViewModel, photoViewModel = photoViewModel, codiViewModel)
            1 -> CoordinationCodiListScreen(navHostController = navHostController, codiList.codiList)
            else -> CoordinationFittingListScreen(navHostController = navHostController, codiList.fittingList)
        }
    }
}