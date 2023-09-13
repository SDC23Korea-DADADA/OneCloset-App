package com.dadada.onecloset.presentation.ui.closet

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.dadada.onecloset.domain.model.Closet
import com.dadada.onecloset.presentation.R
import com.dadada.onecloset.presentation.ui.CoordinationNav
import com.dadada.onecloset.presentation.ui.closet.component.ClosetListView
import com.dadada.onecloset.presentation.ui.common.BottomSheetAddCloset
import com.dadada.onecloset.presentation.ui.common.CustomFloatingActionButton
import com.dadada.onecloset.presentation.ui.common.InfoView
import com.dadada.onecloset.presentation.ui.theme.Blue
import com.dadada.onecloset.presentation.ui.theme.BluePurple
import com.dadada.onecloset.presentation.ui.theme.Gray
import com.dadada.onecloset.presentation.ui.theme.Green
import com.dadada.onecloset.presentation.ui.theme.Navy
import com.dadada.onecloset.presentation.ui.theme.Orange
import com.dadada.onecloset.presentation.ui.theme.Pink
import com.dadada.onecloset.presentation.ui.theme.SkyBlue
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClosetScreen(navHostController: NavHostController) {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    val list = listOf(
        Closet("옷장", R.drawable.ic_date, Blue.toArgb(), ""),
        Closet("옷장", R.drawable.ic_date, BluePurple.toArgb(), ""),
        Closet("옷장", R.drawable.ic_date, Orange.toArgb(), ""),
        Closet("옷장", R.drawable.ic_date, Gray.toArgb(), ""),
    )
    var arr = listOf(
        Closet("옷장", R.drawable.ic_date, Green.toArgb(), ""),
        Closet("옷장", R.drawable.ic_date, Pink.toArgb(), ""),
        Closet("옷장", R.drawable.ic_date, SkyBlue.toArgb(), ""),
        Closet("옷장", R.drawable.ic_date, Navy.toArgb(), ""),
    )
    arr += list

    if (sheetState.isVisible) {
        ModalBottomSheet(
            sheetState = sheetState,
            onDismissRequest = { scope.launch { sheetState.hide() } },
            containerColor = Color.White
        ) {
            BottomSheetAddCloset()
        }
    }

    Scaffold(
        floatingActionButton = {
            CustomFloatingActionButton(modifier = Modifier.padding(bottom = 80.dp),icon = Icons.Default.Add) { scope.launch { sheetState.show() } }
        },
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .padding(top = 24.dp)
                .fillMaxSize()
        ) {
            InfoView(
                title = stringResource(R.string.daily_codi),
                content = stringResource(R.string.daliy_codi_guide),
                onClick = { navHostController.navigate(CoordinationNav.route) }) {
                Icon(
                    modifier = Modifier.size(36.dp),
                    painter = painterResource(id = R.drawable.ic_date),
                    contentDescription = ""
                )
            }

            Spacer(modifier = Modifier.size(16.dp))

            ClosetListView(navHostController = navHostController, closetList = arr)
        }
    }
}
