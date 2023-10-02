package com.dadada.onecloset.presentation.ui.clothes

import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.dadada.onecloset.domain.model.clothes.ClothesInfo
import com.dadada.onecloset.presentation.ui.NavigationItem
import com.dadada.onecloset.presentation.ui.clothes.component.sheet.ClothesPutAdditionalInfoBottomSheet
import com.dadada.onecloset.presentation.ui.clothes.component.view.ClothesCourseView
import com.dadada.onecloset.presentation.ui.clothes.component.view.ClothesInformView
import com.dadada.onecloset.presentation.ui.components.RoundedSquareImageItem
import com.dadada.onecloset.presentation.ui.components.header.CustomHeader
import com.dadada.onecloset.presentation.ui.components.row.CustomTabRow
import com.dadada.onecloset.presentation.ui.theme.roundedSquareLargeModifier
import com.dadada.onecloset.presentation.ui.theme.screenModifier
import com.dadada.onecloset.presentation.ui.utils.NetworkResultHandler
import com.dadada.onecloset.presentation.ui.utils.ShowToast
import com.dadada.onecloset.presentation.viewmodel.MainViewModel
import com.dadada.onecloset.presentation.viewmodel.closet.ClosetViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClothScreen(
    navHostController: NavHostController,
    mainViewModel: MainViewModel,
    clothId: String,
    closetViewModel: ClosetViewModel = hiltViewModel(),
) {
    val clothState by closetViewModel.clothState.collectAsState()
    var cloth by remember { mutableStateOf(ClothesInfo()) }
    val clothDeleteState by closetViewModel.clothDeleteState.collectAsState()
    val clothUpdateState by closetViewModel.clothesUpdatState.collectAsState()

    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    if (sheetState.isVisible) {
        ClothesPutAdditionalInfoBottomSheet(
            sheetState = sheetState,
            cloth = cloth,
            closetViewModel = closetViewModel,
            onDismissRequest = { scope.launch { sheetState.hide() } },
        )
    }

    LaunchedEffect(Unit) {
        closetViewModel.getCloth(clothId)
    }

    NetworkResultHandler(state = clothState, mainViewModel = mainViewModel) {
        cloth = it
    }
    var showToast by remember { mutableStateOf(false) }
    if (showToast) {
        ShowToast(text = "의류가 삭제됐어요.")
    }

    NetworkResultHandler(state = clothDeleteState, mainViewModel = mainViewModel) {
        showToast = true
        navHostController.popBackStack()
    }

    NetworkResultHandler(state = clothUpdateState, mainViewModel = mainViewModel) {
        closetViewModel.getCloth(clothId)
        scope.launch { sheetState.hide() }
    }

    val titleList = listOf("세탁", "건조", "에어드레서")
    val contentList = listOf(cloth.laundry, cloth.dryer, cloth.airDressor)

    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val handleTabClick = { newIndex: Int ->
        selectedTabIndex = newIndex
    }

    val tabs = listOf("코스 정보", "의류 정보")
    val tabWidths = remember {
        val tabWidthStateList = mutableStateListOf<Dp>()
        repeat(tabs.size) {
            tabWidthStateList.add(0.dp)
        }
        tabWidthStateList
    }

    Scaffold(
        topBar = {
            CustomHeader(
                navController = navHostController,
                onClickEdit = { scope.launch { sheetState.show() } },
                onClickDelete = { closetViewModel.deleteCloth(clothId) },
            )
        },
    ) {
        Box(modifier = screenModifier.padding(it)) {
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                RoundedSquareImageItem(
                    modifier = roundedSquareLargeModifier,
                    imageUri = cloth.image.toUri(),
                    icon = null,
                ) {
                    val encodedPath = Uri.encode(cloth.image)
                    navHostController.navigate("${NavigationItem.PhotoNav.route}/$encodedPath")
                }

                CustomTabRow(
                    modifier = Modifier,
                    tabs = tabs,
                    selectedTabIndex = selectedTabIndex,
                    tabWidths = tabWidths,
                    tabClick = handleTabClick,
                )

                when (selectedTabIndex) {
                    0 -> {
                        ClothesCourseView(titleList = titleList, contentList = contentList)
                    }

                    else -> {
                        ClothesInformView(
                            cloth = cloth,
                            onClick = { scope.launch { sheetState.show() } },
                        )
                    }
                }
            }
        }
    }
}
