package com.dadada.onecloset.presentation.ui.closet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.navigation.NavHostController
import com.dadada.onecloset.domain.model.Cloth
import com.dadada.onecloset.presentation.ui.GalleryNav
import com.dadada.onecloset.presentation.ui.component.ClothItemView
import com.dadada.onecloset.presentation.ui.component.CustomTabRow
import com.dadada.onecloset.presentation.ui.theme.PrimaryBlack

@Composable
fun ClosetDetailScreen(navHostController: NavHostController) {
    var selectedTabIndex = remember { mutableStateOf(0) }
    val tabs = listOf("전체", "상의", "하의", "외투", "한벌옷")
    val tabWidths = remember {
        val tabWidthStateList = mutableStateListOf<Dp>()
        repeat(tabs.size) {
            tabWidthStateList.add(0.dp)
        }
        tabWidthStateList
    }
    val list =
        listOf(Cloth(), Cloth(), Cloth(), Cloth(), Cloth(), Cloth(), Cloth(), Cloth(), Cloth())

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .padding(top = 24.dp),
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.padding(bottom = 16.dp),
                contentColor = Color.White,
                containerColor = PrimaryBlack,
                onClick = { navHostController.navigate(GalleryNav.route) }
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "fab")
            }
        },
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(26.dp))
                .background(Color.White)
                .padding(it)
        ) {
            CustomTabRow(
                modifier = Modifier,
                tabs = tabs,
                selectedTabIndex = selectedTabIndex,
                tabWidths = tabWidths
            )
            LazyVerticalGrid(
                modifier = Modifier.padding(horizontal = 4.dp),
                columns = GridCells.Fixed(3),
            ) {
                items(list.size) {
                    ClothItemView(
                        modifier = Modifier.padding(4.dp).clip(RoundedCornerShape(20.dp)),
                        imageUri = list[it].thumbnailImg.toUri()
                    ) {

                    }
                }
            }

        }
    }
}