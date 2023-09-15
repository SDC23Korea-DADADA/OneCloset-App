package com.dadada.onecloset.presentation.ui.closet.component

import android.util.Log
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
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
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
import com.dadada.onecloset.presentation.R
import com.dadada.onecloset.presentation.ui.ClosetDetailNav
import com.dadada.onecloset.presentation.ui.common.CustomTabRow
import com.dadada.onecloset.presentation.ui.common.RoundedSquareIconWithTitleItem
import com.dadada.onecloset.presentation.ui.common.RoundedSquareImageItem
import com.dadada.onecloset.presentation.ui.common.roundedSquareLargeModifier
import com.dadada.onecloset.presentation.ui.common.roundedSquareMediumModifier


@Composable
fun ClothTabGridView(
    paddingValues: PaddingValues = PaddingValues(0.dp),
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    clothItems: List<Cloth> = listOf(),
    icon: Int? = null,
    itemClickedStateList: SnapshotStateList<Boolean> = mutableStateListOf(),
    onClick: (Int) -> Unit = {}
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
        ClothGridView(
            navHostController = navHostController,
            clothItems = clothItems,
            itemClickedStateList = itemClickedStateList,
            onClick = onClick
        )
    }
}

private const val TAG = "View"
@Composable
fun ClothGridView(
    navHostController: NavHostController,
    clothItems: List<Cloth>,
    itemClickedStateList: SnapshotStateList<Boolean> = mutableStateListOf(),
    onClick: (Int) -> Unit = {}
) {
    var icon: Int? = null
    Log.d(TAG, "ClothGridView: ${itemClickedStateList.size}")
    LazyVerticalGrid(
        modifier = Modifier.padding(horizontal = 4.dp),
        columns = GridCells.Fixed(3),
    ) {
        items(clothItems.size) { it ->
           if(itemClickedStateList.size != 0) {
               icon = if(itemClickedStateList[it]) R.drawable.ic_checked else R.drawable.ic_unchecked
           }
            RoundedSquareImageItem(
                modifier = roundedSquareMediumModifier,
                imageUri = clothItems[it].thumbnailImg.toUri(),
                icon = icon,
                onClick = { onClick(it) },
            )
        }
    }
}


@Composable
fun ClosetListView(navHostController: NavHostController, closetList: List<Closet>) {
    Box(
        modifier = roundedSquareLargeModifier
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