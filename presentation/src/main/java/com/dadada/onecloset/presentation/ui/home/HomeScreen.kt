package com.dadada.onecloset.presentation.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.dadada.onecloset.presentation.R
import com.dadada.onecloset.presentation.ui.NavigationItem
import com.dadada.onecloset.presentation.ui.theme.roundedSquareLargeModifier
import com.dadada.onecloset.presentation.ui.common.sheet.FittingModelListBottomSheet
import com.dadada.onecloset.presentation.ui.home.component.card.HomeCustomCard
import com.dadada.onecloset.presentation.ui.theme.Paddings
import com.dadada.onecloset.presentation.ui.theme.Size
import com.dadada.onecloset.presentation.ui.utils.PermissionRequester
import com.dadada.onecloset.presentation.ui.utils.Permissions
import com.dadada.onecloset.presentation.viewmodel.MainViewModel
import com.dadada.onecloset.presentation.viewmodel.fitting.FittingViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navHostController: NavHostController,
    mainViewModel: MainViewModel,
    fittingViewModel: FittingViewModel,
) {
    var onClickCourse by remember { mutableStateOf(false) }
    if (onClickCourse) {
        PermissionRequester(
            permission = Permissions.readExternalStoragePermission,
            onDismissRequest = { onClickCourse = !onClickCourse },
            onPermissionGranted = { navHostController.navigate(NavigationItem.GalleryNav.route) },
            onPermissionDenied = { onClickCourse = !onClickCourse },
        )
    }

    val fittingSheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    if (fittingSheetState.isVisible) {
        FittingModelListBottomSheet(
            navHostController,
            mainViewModel = mainViewModel,
            fittingViewModel = fittingViewModel,
            sheetState = fittingSheetState,
            onDismissRequest = { scope.launch { fittingSheetState.hide() } },
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = Paddings.xlarge),
    ) {
        HomeCustomCard(
            modifier = roundedSquareLargeModifier,
            title = stringResource(R.string.care),
            content = stringResource(R.string.home_care_guide),
            animation = R.raw.animation_course,
            onClick = { onClickCourse = !onClickCourse },
        )

        Spacer(modifier = Modifier.size(Size.extra))

        HomeCustomCard(
            modifier = roundedSquareLargeModifier,
            title = stringResource(R.string.fitting),
            content = stringResource(R.string.home_fitting_guide),
            animation = R.raw.animation_fitting,
            onClick = { scope.launch { fittingSheetState.show() } },
        )
    }
}
