package com.dadada.onecloset.presentation.ui.closet.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.navigation.NavHostController
import com.dadada.onecloset.domain.model.Closet
import com.dadada.onecloset.domain.model.Cloth
import com.dadada.onecloset.presentation.ui.ClosetDetailNav
import com.dadada.onecloset.presentation.ui.ClothNav
import com.dadada.onecloset.presentation.ui.common.ClothItemView
import com.dadada.onecloset.presentation.ui.common.CustomTabRow
import com.dadada.onecloset.presentation.ui.common.RoundedSquareIconWithTitleItem
import com.dadada.onecloset.presentation.ui.common.roundedShapeLargeModifier


@Composable
fun ClothTabGridView(
    paddingValues: PaddingValues = PaddingValues(0.dp),
    modifier: Modifier = Modifier,
    navHostController: NavHostController
) {
    var selectedTabIndex by remember { mutableStateOf(0) }
    val handleTabClick = { newIndex: Int ->
        selectedTabIndex = newIndex
    }

    val tabs = listOf("전체", "상의", "하의", "외투", "한벌옷")
    val tabWidths = remember {
        val tabWidthStateList = mutableStateListOf<Dp>()
        repeat(tabs.size) {
            tabWidthStateList.add(0.dp)
        }
        tabWidthStateList
    }

    var list =
        listOf(Cloth(), Cloth(), Cloth(), Cloth(), Cloth(), Cloth(), Cloth(), Cloth(), Cloth())
    list += list;
    list += list

    Column(
        modifier = modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(26.dp))
            .background(Color.White)
            .padding(paddingValues)
    ) {
        CustomTabRow(
            modifier = Modifier,
            tabs = tabs,
            selectedTabIndex = selectedTabIndex,
            tabWidths = tabWidths,
            tabClick = handleTabClick
        )
        ClothGridView(navHostController = navHostController, clothItems = list)
    }
}

@Composable
fun ClothGridView(navHostController: NavHostController, clothItems: List<Cloth>) {
    LazyVerticalGrid(
        modifier = Modifier.padding(horizontal = 4.dp),
        columns = GridCells.Fixed(3),
    ) {
        items(clothItems.size) {
            ClothItemView(
                modifier = Modifier
                    .padding(4.dp)
                    .clip(RoundedCornerShape(20.dp)),
                imageUri = clothItems[it].thumbnailImg.toUri()
            ) {
                navHostController.navigate(ClothNav.route)
            }
        }
    }
}

@Composable
fun ClosetListView(navHostController: NavHostController, closetList: List<Closet>) {
    Box(
        modifier = roundedShapeLargeModifier.aspectRatio(1f)
    ) {
        LazyVerticalGrid(
            modifier = Modifier
                .align(Alignment.Center),
            columns = GridCells.Fixed(3),
        ) {
            items(closetList.size) {
                RoundedSquareIconWithTitleItem(
                    modifier = Modifier.padding(24.dp),
                    title = closetList[it].name,
                    icon = closetList[it].icon,
                    backGroundTint = Color(closetList[it].iconColor),
                    onClick = { navHostController.navigate(ClosetDetailNav.route) }
                )
            }
        }
    }
}