package com.dadada.onecloset.presentation.ui.closet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.dadada.onecloset.domain.model.Closet
import com.dadada.onecloset.presentation.R
import com.dadada.onecloset.presentation.ui.ClosetDetailNav
import com.dadada.onecloset.presentation.ui.component.BottomSheetAddCloset
import com.dadada.onecloset.presentation.ui.component.ClosetItem
import com.dadada.onecloset.presentation.ui.component.ClosetItemViewWithName
import com.dadada.onecloset.presentation.ui.component.SmallRoundedShape
import com.dadada.onecloset.presentation.ui.theme.PrimaryBlack
import com.dadada.onecloset.presentation.ui.theme.iconBlue
import com.dadada.onecloset.presentation.ui.theme.iconBluePurple
import com.dadada.onecloset.presentation.ui.theme.iconGray
import com.dadada.onecloset.presentation.ui.theme.iconGreen
import com.dadada.onecloset.presentation.ui.theme.iconNavy
import com.dadada.onecloset.presentation.ui.theme.iconOrange
import com.dadada.onecloset.presentation.ui.theme.iconPink
import com.dadada.onecloset.presentation.ui.theme.iconSkyBlue
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClosetScreen(navHostController: NavHostController) {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    val list = listOf(
        Closet("옷장", R.drawable.ic_date, iconBlue.toArgb(),""),
        Closet("옷장", R.drawable.ic_date, iconBluePurple.toArgb(),""),
        Closet("옷장", R.drawable.ic_date, iconOrange.toArgb(),""),
        Closet("옷장", R.drawable.ic_date, iconGray.toArgb(),""),
    )
    var arr = listOf(
        Closet("옷장", R.drawable.ic_date, iconGreen.toArgb(),""),
        Closet("옷장", R.drawable.ic_date, iconPink.toArgb(),""),
        Closet("옷장", R.drawable.ic_date, iconSkyBlue.toArgb(),""),
        Closet("옷장", R.drawable.ic_date, iconNavy.toArgb(),""),
    )
    arr += list
    if (sheetState.isVisible) {
        ModalBottomSheet(
            sheetState = sheetState,
            onDismissRequest = {
                scope.launch {
                    sheetState.hide()
                }
            },
            containerColor = Color.White
        ) {
            BottomSheetAddCloset()
        }
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.padding(bottom = 88.dp),
                contentColor = Color.White,
                containerColor = PrimaryBlack,
                onClick = { scope.launch { sheetState.show() } }
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "fab")
            }
        },
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .padding(top = 24.dp)
                .fillMaxSize()
        ) {
            SmallRoundedShape(
                title = "데일리코디",
                content = "매일 코디를 기획하고 일상을 기록해요.",
                icon = R.drawable.ic_date
            ) {

            }
            Spacer(modifier = Modifier.size(16.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(26.dp))
                    .background(Color.White)
                    .padding(16.dp)
            ) {
                LazyVerticalGrid(
                    modifier = Modifier
                        .align(Alignment.Center),
                    columns = GridCells.Fixed(3),
                ) {
                    items(arr.size) { idx ->
                        ClosetItemViewWithName(
                            item = arr[idx]
                        ) { navHostController.navigate(ClosetDetailNav.route) }
                    }
                }
            }
        }
    }
}
