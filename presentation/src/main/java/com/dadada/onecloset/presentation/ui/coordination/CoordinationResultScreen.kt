package com.dadada.onecloset.presentation.ui.coordination

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.dadada.onecloset.presentation.ui.common.CustomTabRow
import com.dadada.onecloset.presentation.ui.common.roundedSquareLargeModifier
import com.dadada.onecloset.presentation.ui.common.screenModifier
import com.dadada.onecloset.presentation.ui.theme.Paddings
import com.dadada.onecloset.presentation.viewmodel.codi.CodiViewModel

@Composable
fun CoordinationResultScreen(codiViewModel: CodiViewModel) {
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
        modifier = screenModifier.padding(vertical = Paddings.large).padding(bottom = 44.dp)
    ) {
        CustomTabRow(
            modifier = Modifier,
            tabs = tabs,
            selectedTabIndex = selectedTabIndex,
            tabWidths = tabWidths,
            tabClick = handleTabClick
        )

        when(selectedTabIndex) {
            0 -> codiViewModel.curDailyCodiItem?.originImg?.let { ImageView(url = it) }
            else -> codiViewModel.curFittingItem?.fittingImg?.let { ImageView(url = it) }
        }

    }
}

@Composable
fun ImageView(url: String) {
    Box(modifier = roundedSquareLargeModifier) {
        AsyncImage(modifier = Modifier
            .fillMaxSize()
            , model = url, contentDescription = "", contentScale = ContentScale.Crop)
    }
}