package com.dadada.onecloset.presentation.ui.coordi

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import com.dadada.onecloset.presentation.ui.common.header.CustomHeader
import com.dadada.onecloset.presentation.ui.coordi.component.view.CoordiResultView
import com.dadada.onecloset.presentation.ui.theme.screenModifier
import com.dadada.onecloset.presentation.ui.utils.NetworkResultHandler
import com.dadada.onecloset.presentation.ui.utils.ShowToast
import com.dadada.onecloset.presentation.viewmodel.MainViewModel
import com.dadada.onecloset.presentation.viewmodel.codi.CodiViewModel

@Composable
fun CoordiDetailDailyScreen(
    mainViewModel: MainViewModel,
    codiViewModel: CodiViewModel,
    navController: NavHostController,
) {
    val deleteState by codiViewModel.codiDeleteState.collectAsState()
    var showToast by remember { mutableStateOf(false) }
    if (showToast) {
        ShowToast(text = "코디가 삭제됐어요.")
    }
    NetworkResultHandler(state = deleteState, mainViewModel = mainViewModel) {
        codiViewModel.resetNetworkStates()
        showToast = true
        navController.popBackStack()
    }
    Scaffold(
        topBar = {
            CustomHeader(
                navController = navController,
                isEdit = false,
                onClickEdit = { },
                onClickDelete = { codiViewModel.deleteCodi() },
            )
        },
    ) {
        Column(modifier = screenModifier.padding(it)) {
            CoordiResultView(
                imagePath = codiViewModel.curDailyCodiItem.originImg,
                clothesList = codiViewModel.curDailyCodiItem.clothesList,
                navController = navController,
            )
        }
    }
}
