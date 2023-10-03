package com.dadada.onecloset.presentation.ui.coordi

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
import androidx.compose.runtime.mutableIntStateOf
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
import com.dadada.onecloset.presentation.ui.common.button.CustomFloatingActionButton
import com.dadada.onecloset.presentation.ui.common.row.CustomTabRow
import com.dadada.onecloset.presentation.ui.common.sheet.FittingModelListBottomSheet
import com.dadada.onecloset.presentation.ui.coordi.component.view.CoordiCalendarView
import com.dadada.onecloset.presentation.ui.coordi.component.view.CoordiCodiListView
import com.dadada.onecloset.presentation.ui.coordi.component.view.CoordiFittingListView
import com.dadada.onecloset.presentation.ui.theme.Paddings
import com.dadada.onecloset.presentation.ui.theme.roundedSquareLargeModifier
import com.dadada.onecloset.presentation.ui.theme.screenModifier
import com.dadada.onecloset.presentation.ui.utils.NetworkResultHandler
import com.dadada.onecloset.presentation.viewmodel.MainViewModel
import com.dadada.onecloset.presentation.viewmodel.PhotoViewModel
import com.dadada.onecloset.presentation.viewmodel.codi.CodiViewModel
import com.dadada.onecloset.presentation.viewmodel.fitting.FittingViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoordiScreen(
    navHostController: NavHostController,
    mainViewModel: MainViewModel,
    codiViewModel: CodiViewModel,
    photoViewModel: PhotoViewModel,
    fittingViewModel: FittingViewModel,
) {
    val codiListState by codiViewModel.codiListState.collectAsState()
    var codiList by remember { mutableStateOf(CodiList(listOf(), listOf())) }
    NetworkResultHandler(state = codiListState, mainViewModel = mainViewModel) {
        codiList = it
    }

    LaunchedEffect(Unit) {
        codiViewModel.curFittingItem = Fitting()
        codiViewModel.curDailyCodiItem = Codi()
        codiViewModel.getCodiList()
    }

    var selectedTabIndex by remember { mutableIntStateOf(0) }
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
            onDismissRequest = { scope.launch { sheetState.hide() } },
        )
    }

    Scaffold(
        modifier = screenModifier,
        floatingActionButton = {
            CustomFloatingActionButton(title = "계획", icon = Icons.Default.Add) {
                scope.launch { sheetState.show() }
            }
        },
    ) {
        Column {
            CustomTabRow(
                modifier = Modifier.padding(it),
                tabs = tabs,
                selectedTabIndex = selectedTabIndex,
                tabWidths = tabWidths,
                tabClick = handleTabClick,
            )

            when (selectedTabIndex) {
                0 -> CoordiCalendarView(
                    modifier = roundedSquareLargeModifier.padding(Paddings.medium),
                    navController = navHostController,
                    codiViewModel = codiViewModel,
                    photoViewModel = photoViewModel,
                    codiList = codiList,
                ) {
                }

                1 -> CoordiCodiListView(
                    navHostController = navHostController,
                    codiList.codiList,
                    codiViewModel,
                )

                else -> CoordiFittingListView(
                    navHostController = navHostController,
                    codiList.fittingList,
                    codiViewModel,
                )
            }
        }
    }
}
