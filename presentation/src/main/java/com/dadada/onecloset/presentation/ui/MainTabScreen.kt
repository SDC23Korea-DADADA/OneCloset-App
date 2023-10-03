package com.dadada.onecloset.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.dadada.onecloset.presentation.ui.closet.ClosetScreen
import com.dadada.onecloset.presentation.ui.common.row.CustomTabRow
import com.dadada.onecloset.presentation.ui.theme.screenModifier
import com.dadada.onecloset.presentation.ui.home.HomeScreen
import com.dadada.onecloset.presentation.viewmodel.MainViewModel
import com.dadada.onecloset.presentation.viewmodel.fitting.FittingViewModel

@Composable
fun MainTabScreen(navHostController: NavHostController, mainViewModel: MainViewModel, fittingViewModel: FittingViewModel) {
    var selectedTabIndex by rememberSaveable { mutableStateOf(0) }
    val handleTabClick = { newIndex: Int ->
        selectedTabIndex = newIndex
    }

    val tabs = listOf(NavigationItem.HomeNav.title, NavigationItem.ClosetNav.title)
    val tabWidths = remember {
        val tabWidthStateList = mutableStateListOf<Dp>()
        repeat(tabs.size) {
            tabWidthStateList.add(0.dp)
        }
        tabWidthStateList
    }

    Box(
        modifier = screenModifier,
    ) {
        when (selectedTabIndex) {
            0 -> HomeScreen(navHostController, mainViewModel, fittingViewModel)
            else -> ClosetScreen(navHostController, mainViewModel)
        }
        CustomTabRow(
            modifier = Modifier.align(Alignment.BottomCenter),
            tabs = tabs,
            selectedTabIndex = selectedTabIndex,
            tabWidths = tabWidths,
            tabClick = handleTabClick,
        )
    }
}
