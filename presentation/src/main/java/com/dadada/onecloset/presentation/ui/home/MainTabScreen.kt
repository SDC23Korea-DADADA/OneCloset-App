package com.dadada.onecloset.presentation.ui.home

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabPosition
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.dadada.onecloset.presentation.ui.ClosetNav
import com.dadada.onecloset.presentation.ui.HomeNav
import com.dadada.onecloset.presentation.ui.closet.ClosetScreen
import com.dadada.onecloset.presentation.ui.component.CustomTabRow
import kotlinx.coroutines.launch

@Composable
fun MainTabScreen(navHostController: NavHostController) {
    val selectedTabIndex = rememberSaveable { mutableStateOf(0) }
    val tabs = listOf(HomeNav.title, ClosetNav.title)
    val tabWidths = remember {
        val tabWidthStateList = mutableStateListOf<Dp>()
        repeat(tabs.size) {
            tabWidthStateList.add(0.dp)
        }
        tabWidthStateList
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        when (selectedTabIndex.value) {
            0 -> HomeScreen(navHostController)
            else -> ClosetScreen(navHostController)
        }
        CustomTabRow(
            modifier = Modifier.align(Alignment.BottomCenter),
            tabs = tabs,
            selectedTabIndex = selectedTabIndex,
            tabWidths = tabWidths
        )
    }
}


