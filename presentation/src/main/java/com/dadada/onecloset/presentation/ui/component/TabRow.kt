package com.dadada.onecloset.presentation.ui.component

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabPosition
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.dadada.onecloset.presentation.ui.theme.PrimaryBlack
import kotlinx.coroutines.launch

@Composable
fun CustomTabRow(
    modifier: Modifier,
    tabs: List<String>,
    selectedTabIndex: MutableState<Int>,
    tabWidths: MutableList<Dp>
) {
    val density = LocalDensity.current
    val coroutineScope = rememberCoroutineScope()
    TabRow(
        modifier = modifier
            .padding(bottom = 12.dp),
        selectedTabIndex = selectedTabIndex.value,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                modifier = Modifier
                    .customTabIndicatorOffset(
                        tabPositions[selectedTabIndex.value],
                        tabWidths[selectedTabIndex.value]
                    )
                    .graphicsLayer {
                        shape = RoundedCornerShape(
                            topStart = 16.dp,
                            topEnd = 16.dp,
                            bottomStart = 16.dp,
                            bottomEnd = 16.dp
                        )
                        clip = true
                    },
                color = PrimaryBlack
            )
        },
        divider = {},
        containerColor = Color.Transparent
    ) {
        tabs.forEachIndexed { index, title ->
            Tab(
                selected = selectedTabIndex.value == index,
                onClick = {
                    coroutineScope.launch {
                        selectedTabIndex.value = index
                    }
                },
                text = {
                    Text(
                        text = title,
                        color = MaterialTheme.colorScheme.onSurface, //if (selectedTabIndex.value == index) PointDeepGreen else
                        fontWeight = if (selectedTabIndex.value == index) FontWeight.ExtraBold else FontWeight.Normal,
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
        .wrapContentSize(Alignment.BottomStart) // indicator 표시 위치
        .offset(x = indicatorOffset)
        .width(currentTabWidth)
}