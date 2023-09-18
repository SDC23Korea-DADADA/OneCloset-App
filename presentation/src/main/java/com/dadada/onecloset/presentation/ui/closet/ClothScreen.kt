package com.dadada.onecloset.presentation.ui.closet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import com.dadada.onecloset.domain.model.Cloth
import com.dadada.onecloset.presentation.ui.common.ChipEditRow
import com.dadada.onecloset.presentation.ui.common.ColorEditRow
import com.dadada.onecloset.presentation.ui.common.CustomTabRow
import com.dadada.onecloset.presentation.ui.common.RoundedSquare
import com.dadada.onecloset.presentation.ui.common.RoundedSquareImageItem
import com.dadada.onecloset.presentation.ui.common.roundedSquareLargeModifier
import com.dadada.onecloset.presentation.ui.common.screenModifier
import com.dadada.onecloset.presentation.ui.utils.ClothColor
import com.dadada.onecloset.presentation.ui.utils.Material
import com.dadada.onecloset.presentation.ui.utils.Type

@Composable
fun ClothScreen(navHostController: NavHostController) {
    var showType = remember { mutableStateOf(false) }
    var showColor = remember { mutableStateOf(false) }
    var showMaterial = remember { mutableStateOf(false) }

    var type = remember { mutableStateOf<Type>(Type.Blouse) }
    var material = remember { mutableStateOf<Material>(Material.Denim) }
    var color = remember {
        mutableStateOf<ClothColor>(ClothColor.Black)
    }

    val list = listOf("세탁", "건조", "에어드레서")
    val contentList = listOf(
        "울 소재의 옷은 울 전용 세제를 넣어 울 전용 코스를 이용해보세요!",
        "울 소재의 옷은 울 전용 세제를 넣어 울 전용 코스를 이용해보세요!",
        "울 소재의 옷은 울 전용 세제를 넣어 울 전용 코스를 이용해보세요!"
    )

    var selectedTabIndex by remember { mutableStateOf(0) }
    val handleTabClick = { newIndex: Int ->
        selectedTabIndex = newIndex
    }

    val tabs = listOf("코스 정보", "의류 정보")
    val tabWidths = remember {
        val tabWidthStateList = mutableStateListOf<Dp>()
        repeat(tabs.size) {
            tabWidthStateList.add(0.dp)
        }
        tabWidthStateList
    }


    val item = Cloth()
    Box(modifier = screenModifier) {
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            RoundedSquareImageItem(
                modifier = roundedSquareLargeModifier,
                imageUri = item.thumnailImg.toUri(),
                icon = null,
            ) {

            }

            CustomTabRow(
                modifier = Modifier,
                tabs = tabs,
                selectedTabIndex = selectedTabIndex,
                tabWidths = tabWidths,
                tabClick = handleTabClick
            )

            when (selectedTabIndex) {
                0 -> {
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Spacer(modifier = Modifier.weight(1f))
                        Column() {
                            list.forEachIndexed { index, s ->
                                RoundedSquare(title = s, content = contentList[index])
                                Spacer(modifier = Modifier.size(12.dp))
                            }
                        }
                    }
                }

                else -> {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(26.dp))
                            .background(
                                Color.White
                            )
                    ) {
                        Spacer(modifier = Modifier.size(12.dp))
                        ChipEditRow("종류", type.value.name, reverse = showType)
                        ChipEditRow("재질", material.value.name, reverse = showMaterial)
                        ColorEditRow("색상", color.value.color, reverse = showColor)
                        Spacer(modifier = Modifier.size(12.dp))
                    }
                }
            }
        }
    }
}