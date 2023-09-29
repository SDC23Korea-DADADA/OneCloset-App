package com.dadada.onecloset.presentation.ui.coordination

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.dadada.onecloset.domain.model.codi.Codi
import com.dadada.onecloset.domain.model.codi.CodiList
import com.dadada.onecloset.domain.model.codi.Fitting
import com.dadada.onecloset.presentation.ui.account.component.FittingModelListBottomSheet
import com.dadada.onecloset.presentation.ui.common.CustomFloatingActionButton
import com.dadada.onecloset.presentation.ui.common.CustomTabRow
import com.dadada.onecloset.presentation.ui.common.screenModifier
import com.dadada.onecloset.presentation.ui.utils.NetworkResultHandler
import com.dadada.onecloset.presentation.viewmodel.MainViewModel
import com.dadada.onecloset.presentation.viewmodel.PhotoViewModel
import com.dadada.onecloset.presentation.viewmodel.codi.CodiViewModel
import com.dadada.onecloset.presentation.viewmodel.fitting.FittingViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoordinationScreen(
    navHostController: NavHostController,
    mainViewModel: MainViewModel,
    codiViewModel: CodiViewModel,
    photoViewModel: PhotoViewModel,
    fittingViewModel: FittingViewModel
) {
    val codiListState by codiViewModel.codiListByMonth.collectAsState()
    var codiList by remember { mutableStateOf(CodiList(listOf(), listOf())) }
    var showBottomSheet by remember { mutableStateOf(false) }
    NetworkResultHandler(state = codiListState, mainViewModel = mainViewModel) {
        codiList = it
    }


    LaunchedEffect(Unit) {
        codiViewModel.curFittingItem = Fitting()
        codiViewModel.curDailyCodiItem = Codi()
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

    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

    if (sheetState.isVisible) {
        FittingModelListBottomSheet(
            navHostController,
            mainViewModel = mainViewModel,
            fittingViewModel = fittingViewModel,
            sheetState = sheetState,
            onDismissRequest = { scope.launch { sheetState.hide() } })
    }



    Scaffold(
        modifier = screenModifier,
        floatingActionButton = {
            CustomFloatingActionButton(title = "계획", icon = Icons.Default.Add) {
                scope.launch { sheetState.show() }
            }
        }
    ) {
        Column {
            CustomTabRow(
                modifier = Modifier.padding(it),
                tabs = tabs,
                selectedTabIndex = selectedTabIndex,
                tabWidths = tabWidths,
                tabClick = handleTabClick
            )


            when (selectedTabIndex) {
                0 -> CoordinationCalendarScreen(
                    navHostController = navHostController,
                    codiViewModel,
                    photoViewModel = photoViewModel,
                    codiList,
                )

                1 -> CoordinationCodiListScreen(
                    navHostController = navHostController,
                    codiList.codiList,
                    codiViewModel
                )

                else -> CoordinationFittingListScreen(
                    navHostController = navHostController,
                    codiList.fittingList,
                    codiViewModel
                )
            }
        }
    }
}