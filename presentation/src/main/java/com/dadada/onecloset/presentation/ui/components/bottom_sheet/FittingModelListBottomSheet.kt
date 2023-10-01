package com.dadada.onecloset.presentation.ui.components.bottom_sheet

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.dadada.onecloset.domain.model.fitting.FittingModelInfo
import com.dadada.onecloset.presentation.ui.account.component.ModelListView
import com.dadada.onecloset.presentation.ui.theme.BackGround
import com.dadada.onecloset.presentation.ui.utils.NetworkResultHandler
import com.dadada.onecloset.presentation.viewmodel.MainViewModel
import com.dadada.onecloset.presentation.viewmodel.fitting.FittingViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FittingModelListBottomSheet(
    navHostController: NavHostController,
    mainViewModel: MainViewModel,
    fittingViewModel: FittingViewModel,
    sheetState: SheetState,
    onDismissRequest: () -> Unit,
) {
    val modelListState by fittingViewModel.modelListState.collectAsState()
    var modelList by remember {
        mutableStateOf<List<FittingModelInfo>>(listOf())
    }

    LaunchedEffect(Unit) {
        fittingViewModel.getModelList()
    }

    NetworkResultHandler(state = modelListState, mainViewModel = mainViewModel) {
        modelList = it
    }

    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = {
            onDismissRequest()
        },
        containerColor = BackGround,
    ) {
        ModelListView(navHostController, modelList, sheetState, fittingViewModel)
        Spacer(modifier = Modifier.size(56.dp))
    }
}
