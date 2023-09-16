package com.dadada.onecloset.presentation.ui.coordination

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.dadada.onecloset.presentation.ui.common.CustomTabRow
import com.dadada.onecloset.presentation.ui.common.screenModifier
import com.dadada.onecloset.presentation.ui.theme.Paddings

@Composable
fun CoordinationResultScreen() {
    var selectedTabIndex by remember { mutableStateOf(0) }
    val handleTabClick = { newIndex: Int ->
        selectedTabIndex = newIndex
    }

    val tabs = listOf("데일리 코디", "계획")
    val tabWidths = remember {
        val tabWidthStateList = mutableStateListOf<Dp>()
        repeat(tabs.size) {
            tabWidthStateList.add(0.dp)
        }
        tabWidthStateList
    }

    Column(
        modifier = screenModifier
    ) {
        CustomTabRow(
            modifier = Modifier,
            tabs = tabs,
            selectedTabIndex = selectedTabIndex,
            tabWidths = tabWidths,
            tabClick = handleTabClick
        )

        when(selectedTabIndex) {
            0 -> AsyncImage(modifier = Modifier.fillMaxWidth().aspectRatio(1f).padding(Paddings.medium), model = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTJYvG5amIx6hzlcqkFTa4hr_A5YBP2wysJej7q7_Nw&s", contentDescription = "")
            else -> AsyncImage(modifier = Modifier.fillMaxWidth().aspectRatio(1f).padding(Paddings.medium), model = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSZOkCa0X96Hxvh1OMzkafh2FyQcl7yo1ir4a-gVUME&s", contentDescription = "")
        }

    }
}