package com.dadada.onecloset.presentation.ui.closet

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.dadada.onecloset.presentation.ui.ClothCourseNav
import com.dadada.onecloset.presentation.ui.ClothNav
import com.dadada.onecloset.presentation.ui.common.ChipEditRow
import com.dadada.onecloset.presentation.ui.common.ColorEditRow
import com.dadada.onecloset.presentation.ui.common.RoundedSquareImageItem
import com.dadada.onecloset.presentation.ui.common.RowWithTwoButtons
import com.dadada.onecloset.presentation.ui.common.SelectColorBottomSheet
import com.dadada.onecloset.presentation.ui.common.SelectMaterialBottomSheet
import com.dadada.onecloset.presentation.ui.common.SelectTypeBottomSheet
import com.dadada.onecloset.presentation.ui.common.roundedSquareLargeModifier
import com.dadada.onecloset.presentation.ui.theme.Gray
import com.dadada.onecloset.presentation.ui.theme.Typography
import com.dadada.onecloset.presentation.ui.utils.ClothColor
import com.dadada.onecloset.presentation.ui.utils.Material
import com.dadada.onecloset.presentation.ui.utils.Type

@Composable
fun ClothAnalysisScreen(navHostController: NavHostController, photoUri: Uri) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        RoundedSquareImageItem(
            modifier = roundedSquareLargeModifier,
            imageUri = photoUri,
            icon = null,
        ) {

        }
        Spacer(modifier = Modifier.size(16.dp))
        ClothCreateInputView()
        Spacer(modifier = Modifier.size(16.dp))
        Text(
            text = "*분석 결과가 정확한가요? 버튼을 클릭하면 수정할 수 있어요!",
            style = Typography.titleSmall.copy(color = Gray)
        )
        Spacer(modifier = Modifier.weight(1f))
        RowWithTwoButtons(left = "다시하기", right = "다음", onClickLeft = { /*TODO*/ }) {
            val encodedUri = Uri.encode(photoUri.toString())
            navHostController.navigate(ClothNav.route)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClothCreateInputView() {
    var showType = remember { mutableStateOf(false) }
    var showColor = remember { mutableStateOf(false) }
    var showMaterial = remember { mutableStateOf(false) }

    var type = remember { mutableStateOf<Type>(Type.Blouse) }
    var material = remember { mutableStateOf<Material>(Material.Denim) }
    var color = remember {
        mutableStateOf<ClothColor>(ClothColor.Black)
    }
    if (showType.value) {
        SelectTypeBottomSheet(show = showType, type)
    }

    if (showMaterial.value) {
        SelectMaterialBottomSheet(show = showMaterial, curMaterial = material)
    }

    if (showColor.value) {
        SelectColorBottomSheet(show = showColor, curColor = color)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(26.dp))
            .background(
                Color.White
            )
    ) {
        Spacer(modifier = Modifier.size(12.dp))
        ChipEditRow("종류", type.value.name, reverse = showType)
        ChipEditRow("재질", material.value.name, reverse = showMaterial)
        ColorEditRow("색상", color.value.color, reverse = showColor)
        Spacer(modifier = Modifier.size(12.dp))
    }
}