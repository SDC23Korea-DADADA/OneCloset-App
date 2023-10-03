package com.dadada.onecloset.presentation.ui.clothes.component.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.unit.dp
import com.dadada.onecloset.domain.model.clothes.ClothesInfo
import com.dadada.onecloset.presentation.R
import com.dadada.onecloset.presentation.ui.clothes.component.row.ClothesColorEditRow
import com.dadada.onecloset.presentation.ui.clothes.component.row.ClothesInfoEditRow
import com.dadada.onecloset.presentation.ui.clothes.component.sheet.SelectClothesInfoBottomSheet
import com.dadada.onecloset.presentation.ui.clothes.component.sheet.SelectColorBottomSheet
import com.dadada.onecloset.presentation.ui.utils.Material
import com.dadada.onecloset.presentation.ui.utils.Type
import com.dadada.onecloset.presentation.ui.utils.colorToHexString
import com.dadada.onecloset.presentation.ui.utils.hexStringToColor
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClothesAnalysisView(modifier: Modifier = Modifier, clothesInfo: ClothesInfo) {
    val scope = rememberCoroutineScope()
    val typeSheetState = rememberModalBottomSheetState()
    val materialSheetState = rememberModalBottomSheetState()
    val colorSheetState = rememberModalBottomSheetState()

    var type by remember { mutableStateOf(clothesInfo.type) }
    var material by remember { mutableStateOf(clothesInfo.material) }
    var colorCode by remember { mutableStateOf(clothesInfo.colorCode) }
    var colorName by remember { mutableStateOf(clothesInfo.color) }

    if (typeSheetState.isVisible) {
        SelectClothesInfoBottomSheet(
            sheetState = typeSheetState,
            title = "종류 선택",
            contentList = Type.getAllTypes().map { it.name },
            onDismissRequest = { scope.launch { typeSheetState.hide() } },
            onClick = {
                type = it
                clothesInfo.type = it
            },
        )
    }

    if (materialSheetState.isVisible) {
        SelectClothesInfoBottomSheet(
            sheetState = materialSheetState,
            title = "재질 선택",
            contentList = Material.getAllMaterial().map { it.name },
            onDismissRequest = { scope.launch { materialSheetState.hide() } },
            onClick = {
                material = it
                clothesInfo.material = material
            },
        )
    }

    if (colorSheetState.isVisible) {
        SelectColorBottomSheet(
            sheetState = colorSheetState,
            onDismissRequest = { scope.launch { colorSheetState.hide() } },
        ) {
            colorCode = colorToHexString(it.color)
            colorName = it.name
            clothesInfo.colorCode = colorCode
            clothesInfo.color = colorName
        }
    }

    Column(
        modifier = modifier,
    ) {
        Spacer(modifier = Modifier.size(12.dp))
        ClothesInfoEditRow(
            stringResource(id = R.string.upper_type),
            type,
            sheetState = typeSheetState,
            onClick = { scope.launch { typeSheetState.show() } },
        )
        ClothesInfoEditRow(
            stringResource(id = R.string.material),
            material,
            sheetState = materialSheetState,
            onClick = { scope.launch { materialSheetState.show() } },
        )
        ClothesColorEditRow(
            stringResource(id = R.string.color),
            hexStringToColor(colorCode),
            name = colorName,
            sheetState = colorSheetState,
            onClick = { scope.launch { colorSheetState.show() } },
        )
        Spacer(modifier = Modifier.size(12.dp))
    }
}
