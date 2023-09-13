package com.dadada.onecloset.presentation.ui.coordination

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.dadada.onecloset.presentation.ui.FittingNav
import com.dadada.onecloset.presentation.ui.GalleryNav
import com.dadada.onecloset.presentation.ui.common.CustomFloatingActionButton
import com.dadada.onecloset.presentation.ui.coordination.component.HorizontalCalendar
import com.dadada.onecloset.presentation.ui.coordination.component.SelectCodiView
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoordinationScreen(navHostController: NavHostController) {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    if (sheetState.isVisible) {
        ModalBottomSheet(
            sheetState = sheetState,
            onDismissRequest = { scope.launch { sheetState.hide() } },
            containerColor = Color.White
        ) {
            SelectCodiView(onClickRecord = { navHostController.navigate(GalleryNav.route) }) {
                navHostController.navigate(FittingNav.route)
            }
        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        floatingActionButton = { CustomFloatingActionButton(icon = Icons.Default.Add) { scope.launch { sheetState.show() } } }
    ) {
        it
        HorizontalCalendar(modifier = Modifier.fillMaxSize()) {

        }
    }


}