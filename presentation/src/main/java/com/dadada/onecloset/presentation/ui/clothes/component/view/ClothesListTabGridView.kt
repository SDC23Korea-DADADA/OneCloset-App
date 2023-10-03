package com.dadada.onecloset.presentation.ui.clothes.component.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
import com.dadada.onecloset.domain.model.clothes.ClothesInfo
import com.dadada.onecloset.presentation.ui.common.row.CustomTabRow
import com.dadada.onecloset.presentation.ui.theme.BackGroundGray
import com.dadada.onecloset.presentation.ui.theme.Paddings
import com.dadada.onecloset.presentation.ui.theme.TextGray
import com.dadada.onecloset.presentation.ui.theme.Typography

@Composable
fun ClothesListTabGridView(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues = PaddingValues(0.dp),
    clothItems: List<ClothesInfo> = listOf(),
    icon: Int? = null,
    isSearch: Boolean = true,
    itemClickedStateList: List<Boolean> = mutableStateListOf(),
    onClick: (Int) -> Unit = {},
    onClickTab: (String) -> Unit = {},
    tabs: List<String> = listOf("전체", "상의", "하의", "외투", "한벌옷"),
) {
    var selectedTabIndex by remember(tabs) { mutableIntStateOf(0) }
    val tabWidths = remember(tabs) {
        val tabWidthStateList = mutableStateListOf<Dp>()
        repeat(tabs.size) {
            tabWidthStateList.add(0.dp)
        }
        tabWidthStateList
    }

    val handleTabClick = { newIndex: Int ->
        selectedTabIndex = newIndex
        onClickTab(tabs[selectedTabIndex])
    }
    var searchQuery by remember { mutableStateOf("") }

    val filteredClothItems = if (searchQuery.isBlank()) {
        clothItems
    } else {
        clothItems.filter {
            it.airDressor.contains(searchQuery, ignoreCase = true) ||
                it.color.contains(searchQuery, ignoreCase = true) ||
                it.description.contains(searchQuery, ignoreCase = true) ||
                it.dryer.contains(searchQuery, ignoreCase = true) ||
                it.laundry.contains(searchQuery, ignoreCase = true) ||
                it.material.contains(searchQuery, ignoreCase = true) ||
                it.type.contains(searchQuery, ignoreCase = true) ||
                it.upperType.contains(searchQuery, ignoreCase = true) ||
                it.hashtagList.any { hashtag ->
                    hashtag.contains(
                        searchQuery,
                        ignoreCase = true,
                    )
                } ||
                it.tpoList.any { tpo -> tpo.contains(searchQuery, ignoreCase = true) } ||
                it.weatherList.any { weather ->
                    weather.contains(
                        searchQuery,
                        ignoreCase = true,
                    )
                }
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(26.dp))
            .background(Color.White)
            .padding(paddingValues),
    ) {
        CustomTabRow(
            modifier = Modifier,
            tabs = tabs,
            selectedTabIndex = selectedTabIndex,
            tabWidths = tabWidths,
            tabClick = handleTabClick,
        )
        if (isSearch) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "",
                    tint = BackGroundGray,
                )
                Spacer(modifier = Modifier.size(Paddings.medium))
                BasicTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    modifier = Modifier
                        .fillMaxWidth(),
                ) {
                    if (searchQuery.isEmpty()) {
                        Text(
                            text = "검색어를 입력하세요.",
                            style = Typography.bodyMedium.copy(color = TextGray),
                        )
                    }
                    it()
                }
            }
        }
        ClothesListGridView(
            clothItems = filteredClothItems,
            icon = icon,
            itemClickedStateList = itemClickedStateList,
            onClick = onClick,
        )
    }
}
