package com.dadada.onecloset.presentation.ui.closet

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomSheetScaffoldState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetValue
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
import com.dadada.onecloset.presentation.ui.closet.component.ClosetListView
import com.dadada.onecloset.presentation.ui.common.BottomSheetAddCloset
import com.dadada.onecloset.presentation.ui.common.CustomFloatingActionButton
import com.dadada.onecloset.presentation.ui.common.InfoView
import com.dadada.onecloset.presentation.ui.utils.NetworkResultHandler
import com.dadada.onecloset.presentation.viewmodel.closet.ClosetViewModel
import kotlinx.coroutines.launch

private const val TAG = "ClosetScreen"

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun ClosetScreen(
    navHostController: NavHostController,
    closetViewModel: ClosetViewModel = hiltViewModel()
) {

    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    val closetListState by closetViewModel.closetListState.collectAsState()
    val networkResultState by closetViewModel.networkResultState.collectAsState()
    var closetList by remember { mutableStateOf(listOf<Closet>()) }


    LaunchedEffect(closetListState) {
        closetViewModel.getClosetList()
    }

    NetworkResultHandler(state = closetListState) {
        closetList = it
    }

    NetworkResultHandler(state = networkResultState) {
        closetViewModel.getClosetList()
        scope.launch { sheetState.hide() }
    }

    var showDialog by remember { mutableStateOf(false) }
    if (sheetState.isVisible) {
        ModalBottomSheet(
            sheetState = sheetState,
            onDismissRequest = { scope.launch { sheetState.hide() } },
            containerColor = Color.White
        ) {
            BottomSheetAddCloset(closetViewModel)
        }
        
//        AlertDialog(onDismissRequest = { /*TODO*/ }, confirmButton = { /*TODO*/ }, text = {
//            BottomSheetAddCloset(
//                closetViewModel = closetViewModel
//            )
//        })
    }

    Scaffold(
        floatingActionButton = {
            CustomFloatingActionButton(
                modifier = Modifier.padding(bottom = 80.dp),
                icon = Icons.Default.Add
            ) { scope.launch { sheetState.show() } }
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
                onClick = { navHostController.navigate(NavigationItem.CoordinationNav.route) }) {
                Icon(
                    modifier = Modifier.size(36.dp),
                    painter = painterResource(id = R.drawable.ic_date),
                    contentDescription = ""
                )
            }

            Spacer(modifier = Modifier.size(16.dp))

            ClosetListView(
                closetList = closetList,
            ) {
                closetViewModel.setSelectedId(it.toString())
                navHostController.navigate(NavigationItem.ClosetDetailNav.route)
            }
        }
    }
}
