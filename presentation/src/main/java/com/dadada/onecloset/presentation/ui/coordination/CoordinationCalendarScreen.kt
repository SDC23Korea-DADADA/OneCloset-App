package com.dadada.onecloset.presentation.ui.coordination

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import com.dadada.onecloset.domain.model.codi.CodiList
import com.dadada.onecloset.presentation.ui.common.roundedSquareLargeModifier
import com.dadada.onecloset.presentation.ui.coordination.component.HorizontalCalendar
import com.dadada.onecloset.presentation.ui.theme.Paddings
import com.dadada.onecloset.presentation.viewmodel.PhotoViewModel
import com.dadada.onecloset.presentation.viewmodel.codi.CodiViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoordinationCalendarScreen(
    navHostController: NavHostController,
    codiViewModel: CodiViewModel,
    photoViewModel: PhotoViewModel,
    codiList: CodiList,
) {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    if (sheetState.isVisible) {

    }

    HorizontalCalendar(
        modifier = roundedSquareLargeModifier.padding(Paddings.medium),
        navController = navHostController,
        codiViewModel = codiViewModel,
        photoViewModel = photoViewModel,
        codiList = codiList
    ) {


    }
}