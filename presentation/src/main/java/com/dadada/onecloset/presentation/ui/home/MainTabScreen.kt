package com.dadada.onecloset.presentation.ui.home

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
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
import com.dadada.onecloset.presentation.ui.ClosetNav
import com.dadada.onecloset.presentation.ui.HomeNav
import com.dadada.onecloset.presentation.ui.closet.ClosetScreen
import kotlinx.coroutines.launch

@Composable
fun MainTabScreen() {
    val coroutineScope = rememberCoroutineScope()
    var selectedTabIndex by remember { mutableStateOf(0) }
    val tabs = listOf(HomeNav.title, ClosetNav.title)
    val density = LocalDensity.current
    val tabWidths = remember {
        val tabWidthStateList = mutableStateListOf<Dp>()
        repeat(tabs.size) {
            tabWidthStateList.add(0.dp)
        }
        tabWidthStateList
    }

    Box(modifier = Modifier.fillMaxSize()) {
        when (selectedTabIndex) {
            0 -> HomeScreen()
            else -> ClosetScreen()
        }

        TabRow(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 12.dp),
            selectedTabIndex = selectedTabIndex,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    modifier = Modifier
                        .customTabIndicatorOffset(
                            tabPositions[selectedTabIndex],
                            tabWidths[selectedTabIndex]
                        ),
                    color = Color.Black
                )
            },
            divider = {}
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = {
                        coroutineScope.launch {
                            selectedTabIndex = index
                        }
                    },
                    text = {
                        Text(
                            text = title,
                            color = MaterialTheme.colorScheme.onSurface,
                            fontWeight = if (selectedTabIndex == index) FontWeight.ExtraBold else FontWeight.Normal,
                            onTextLayout = { textLayoutResult ->
                                tabWidths[index] =
                                    with(density) { textLayoutResult.size.width.toDp() }
                            }
                        )
                    }
                )
            }
        }

    }
}

fun Modifier.customTabIndicatorOffset(
    currentTabPosition: TabPosition,
    tabWidth: Dp
): Modifier = composed(
    inspectorInfo = debugInspectorInfo {
        name = "customTabIndicatorOffset"
        value = currentTabPosition
    }
) {
    val currentTabWidth by animateDpAsState(
        targetValue = tabWidth,
        animationSpec = tween(durationMillis = 250, easing = FastOutSlowInEasing), label = ""
    )
    val indicatorOffset by animateDpAsState(
        targetValue = ((currentTabPosition.left + currentTabPosition.right - tabWidth) / 2),
        animationSpec = tween(durationMillis = 250, easing = FastOutSlowInEasing), label = ""
    )
    fillMaxWidth()
        .wrapContentSize(Alignment.BottomStart)
        .offset(x = indicatorOffset)
        .width(currentTabWidth)
}

