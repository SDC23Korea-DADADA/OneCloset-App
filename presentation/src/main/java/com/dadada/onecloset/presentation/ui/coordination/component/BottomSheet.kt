package com.dadada.onecloset.presentation.ui.coordination.component

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecordCodiBottomSheet(onClickRecord:() -> Unit, onClickPlan:() -> Unit, onDismissRequest: () -> Unit) {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    ModalBottomSheet(onDismissRequest = {
        scope.launch {
            onDismissRequest()
            sheetState.hide()
        }
    }) {
        SelectCodiView(onClickRecord = { onClickRecord() }) {
            onClickPlan()
        }
    }
}
