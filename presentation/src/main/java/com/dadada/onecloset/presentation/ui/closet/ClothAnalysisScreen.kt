package com.dadada.onecloset.presentation.ui.closet

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.navigation.NavHostController
import com.dadada.onecloset.domain.model.clothes.ClothesInfo
import com.dadada.onecloset.presentation.ui.NavigationItem
import com.dadada.onecloset.presentation.ui.common.ChipEditRow
import com.dadada.onecloset.presentation.ui.common.ColorEditRow
import com.dadada.onecloset.presentation.ui.common.RoundedSquareImageItem
import com.dadada.onecloset.presentation.ui.common.RowWithTwoButtons
import com.dadada.onecloset.presentation.ui.common.SelectColorBottomSheet
import com.dadada.onecloset.presentation.ui.common.SelectMaterialBottomSheet
import com.dadada.onecloset.presentation.ui.common.SelectTypeBottomSheet
import com.dadada.onecloset.presentation.ui.common.roundedSquareLargeModifier
import com.dadada.onecloset.presentation.ui.common.screenModifier
import com.dadada.onecloset.presentation.ui.theme.Gray
import com.dadada.onecloset.presentation.ui.theme.Typography
import com.dadada.onecloset.presentation.ui.utils.NetworkResultHandler
import com.dadada.onecloset.presentation.ui.utils.colorToHexString
import com.dadada.onecloset.presentation.ui.utils.hexStringToColor
import com.dadada.onecloset.presentation.viewmodel.closet.ClosetViewModel


private const val TAG = "ClothAnalysisScreen"
@Composable
fun ClothAnalysisScreen(navHostController: NavHostController, closetViewModel: ClosetViewModel) {
    val clothCareCourseState by closetViewModel.clothCareCourseState.collectAsState()
    NetworkResultHandler(state = clothCareCourseState) {
        closetViewModel.clothesInfo.laundry = it.laundry
        closetViewModel.clothesInfo.dryer = it.dryer
        closetViewModel.clothesInfo.airDressor = it.airDresser
        closetViewModel.resetNetworkStates()
        navHostController.navigate(NavigationItem.ClothCourseNav.route)
    }

    Column(
        modifier = screenModifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        RoundedSquareImageItem(
            modifier = roundedSquareLargeModifier,
            imageUri = closetViewModel.clothesInfo.image.toUri(),
            icon = null,
        ) {

        }
        Spacer(modifier = Modifier.size(16.dp))
        ClothCreateInputView(closetViewModel.clothesInfo)
        Spacer(modifier = Modifier.size(16.dp))
        Text(
            text = "*분석 결과가 정확한가요? 버튼을 클릭하면 수정할 수 있어요!",
            style = Typography.titleSmall.copy(color = Gray)
        )
        Spacer(modifier = Modifier.weight(1f))
        RowWithTwoButtons(left = "다시하기", right = "추천받기", onClickLeft = { /*TODO*/ }) {
            closetViewModel.getClothCare()
        }
    }
}

@Composable
fun ClothCreateInputView(cloth: ClothesInfo) {
    var showType = remember { mutableStateOf(false) }
    var showColor = remember { mutableStateOf(false) }
    var showMaterial = remember { mutableStateOf(false) }

    var type by remember { mutableStateOf(cloth.type) }
    var material by remember { mutableStateOf(cloth.material) }
    var colorCode by remember { mutableStateOf(cloth.colorCode) }
    var colorName by remember { mutableStateOf(cloth.color) }


    if (showType.value) {
        SelectTypeBottomSheet(show = showType, type) {
            type = it
            cloth.type = it
        }
    }

    if (showMaterial.value) {
        SelectMaterialBottomSheet(show = showMaterial, curMaterial = material) {
            material = it
            cloth.material = it
        }
    }

    if (showColor.value) {
        SelectColorBottomSheet(show = showColor, curColor = colorCode) {
            colorCode = colorToHexString(it.color)
            cloth.colorCode = colorToHexString(it.color)
            colorName = it.name
        }
    }

    Column(
        modifier = roundedSquareLargeModifier
    ) {
        Spacer(modifier = Modifier.size(12.dp))
        ChipEditRow("종류", type, reverse = showType)
        ChipEditRow("재질", material, reverse = showMaterial)
        ColorEditRow("색상", hexStringToColor(colorCode), name = colorName, reverse = showColor)
        Spacer(modifier = Modifier.size(12.dp))
    }
}