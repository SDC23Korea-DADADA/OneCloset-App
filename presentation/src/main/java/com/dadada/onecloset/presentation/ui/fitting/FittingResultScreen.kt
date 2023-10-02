package com.dadada.onecloset.presentation.ui.fitting

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.dadada.onecloset.presentation.ui.NavigationItem
import com.dadada.onecloset.presentation.ui.components.row.CustomTabRow
import com.dadada.onecloset.presentation.ui.components.row.TwoButtonRow
import com.dadada.onecloset.presentation.ui.fitting.component.dialog.FittingDatePickerDialog
import com.dadada.onecloset.presentation.ui.theme.Paddings
import com.dadada.onecloset.presentation.ui.theme.screenModifier
import com.dadada.onecloset.presentation.ui.utils.NetworkResultHandler
import com.dadada.onecloset.presentation.ui.utils.ShowToast
import com.dadada.onecloset.presentation.viewmodel.MainViewModel
import com.dadada.onecloset.presentation.viewmodel.fitting.FittingViewModel

@Composable
fun FittingResultScreen(
    navHostController: NavHostController,
    mainViewModel: MainViewModel,
    fittingViewModel: FittingViewModel,
) {
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val handleTabClick = { newIndex: Int ->
        selectedTabIndex = newIndex
    }

    val tabs = listOf("원본 사진", "가상 피팅")
    val tabWidths = remember {
        val tabWidthStateList = mutableStateListOf<Dp>()
        repeat(tabs.size) {
            tabWidthStateList.add(0.dp)
        }
        tabWidthStateList
    }

    var date by remember {
        mutableStateOf("Open date picker dialog")
    }

    var showDatePicker by remember {
        mutableStateOf(false)
    }
    var showToast by remember { mutableStateOf(false) }
    if (showToast) {
        ShowToast(text = "피팅 결과가 저장됐어요!")
    }
    val putResultState by fittingViewModel.fittingPutState.collectAsState()
    NetworkResultHandler(state = putResultState, mainViewModel = mainViewModel) {
        fittingViewModel.resetNetworkStates()
        showToast = true
        navHostController.navigate(NavigationItem.CoordinationNav.route) {
            popUpTo(NavigationItem.FittingNav.route) { inclusive = true }
        }
    }

    if (showDatePicker) {
        FittingDatePickerDialog(
            onDateSelected = { date = it },
            onDismiss = { showDatePicker = false },
            onPass = {
                fittingViewModel.fittingResultForSave.fittingImg =
                    fittingViewModel.fittingResult.fittingImg
                fittingViewModel.putFittingResult()
            },
            onPlan = {
                fittingViewModel.fittingResultForSave.fittingImg =
                    fittingViewModel.fittingResult.fittingImg
                fittingViewModel.putFittingResultWithDate(it)
            },
        )
    }

    Column(
        modifier = screenModifier,
    ) {
        CustomTabRow(
            modifier = Modifier,
            tabs = tabs,
            selectedTabIndex = selectedTabIndex,
            tabWidths = tabWidths,
            tabClick = handleTabClick,
        )

        Spacer(modifier = Modifier.weight(1f))
        when (selectedTabIndex) {
            0 -> AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Paddings.xlarge)
                    .clip(RoundedCornerShape(26.dp)),
                model = fittingViewModel.fittingResult.originImg,
                contentDescription = "",
            )

            else -> AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Paddings.xlarge)
                    .clip(RoundedCornerShape(26.dp)),
                model = fittingViewModel.fittingResult.fittingImg,
                contentDescription = "",
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        TwoButtonRow(left = "취소", right = "저장", onClickLeft = {
            navHostController.navigate(NavigationItem.MainTabNav.route) {
                popUpTo(NavigationItem.MainTabNav.route) { inclusive = true }
            }
        }) {
            showDatePicker = !showDatePicker
        }
    }
}
