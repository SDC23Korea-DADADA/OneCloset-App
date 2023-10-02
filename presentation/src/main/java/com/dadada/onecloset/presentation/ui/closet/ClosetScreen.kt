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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.dadada.onecloset.domain.model.Closet
import com.dadada.onecloset.presentation.R
import com.dadada.onecloset.presentation.ui.NavigationItem
import com.dadada.onecloset.presentation.ui.closet.component.sheet.ClosetAddBottomSheet
import com.dadada.onecloset.presentation.ui.closet.component.view.ClosetListView
import com.dadada.onecloset.presentation.ui.components.button.CustomFloatingActionButton
import com.dadada.onecloset.presentation.ui.components.InfoView
import com.dadada.onecloset.presentation.ui.theme.Green
import com.dadada.onecloset.presentation.ui.utils.NetworkResultHandler
import com.dadada.onecloset.presentation.ui.utils.ShowToast
import com.dadada.onecloset.presentation.viewmodel.MainViewModel
import com.dadada.onecloset.presentation.viewmodel.closet.ClosetViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClosetScreen(
    navHostController: NavHostController,
    mainViewModel: MainViewModel,
    closetViewModel: ClosetViewModel = hiltViewModel(),
) {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    val closetListState by closetViewModel.closetListState.collectAsState()
    val networkResultState by closetViewModel.networkResultState.collectAsState()
    var closetList by remember { mutableStateOf(listOf<Closet>()) }

    LaunchedEffect(closetListState) {
        closetViewModel.getClosetList()
    }

    NetworkResultHandler(state = closetListState, mainViewModel = mainViewModel) {
        closetList = it
    }

    var showToast by remember {
        mutableStateOf(false)
    }
    if (showToast) {
        ShowToast(text = "옷장이 등록됐어요.")
    }
    NetworkResultHandler(state = networkResultState, mainViewModel = mainViewModel) {
        closetViewModel.getClosetList()
        showToast = true
        scope.launch { sheetState.hide() }
    }

    if (sheetState.isVisible) {
        ModalBottomSheet(
            sheetState = sheetState,
            onDismissRequest = { scope.launch { sheetState.hide() } },
            containerColor = Color.White,
        ) {
            ClosetAddBottomSheet(closetViewModel)
            Spacer(modifier = Modifier.size(56.dp))
        }
    }

    Scaffold(
        floatingActionButton = {
            CustomFloatingActionButton(
                modifier = Modifier.padding(bottom = 80.dp),
                title = "옷장",
                icon = Icons.Default.Add,
            ) { scope.launch { sheetState.show() } }
        },
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .padding(top = 24.dp)
                .fillMaxSize(),
        ) {
            InfoView(
                title = stringResource(R.string.daily_codi),
                content = stringResource(R.string.daliy_codi_guide),
                onClick = { navHostController.navigate(NavigationItem.CoordinationNav.route) },
            ) {
                Icon(
                    modifier = Modifier.size(44.dp),
                    painter = painterResource(id = R.drawable.ic_daily),
                    contentDescription = "",
                    tint = Green,
                )
            }

            Spacer(modifier = Modifier.size(16.dp))

            ClosetListView(
                closetList = closetList,
                closetViewModel,
            ) { closetId ->
                closetViewModel.setSelectedId(closetId.toString())
                navHostController.navigate(NavigationItem.ClosetDetailNav.route)
            }
        }
    }
}
