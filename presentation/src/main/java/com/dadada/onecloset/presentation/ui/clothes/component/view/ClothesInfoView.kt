package com.dadada.onecloset.presentation.ui.clothes.component.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.dadada.onecloset.domain.model.clothes.ClothesInfo
import com.dadada.onecloset.presentation.R
import com.dadada.onecloset.presentation.ui.closet.component.ClothAdditionalInformView
import com.dadada.onecloset.presentation.ui.closet.component.TipCard
import com.dadada.onecloset.presentation.ui.components.ClothInformRow
import com.dadada.onecloset.presentation.ui.components.ColorInformRow
import com.dadada.onecloset.presentation.ui.components.roundedSquareLargeModifier
import com.dadada.onecloset.presentation.ui.theme.Paddings
import com.dadada.onecloset.presentation.ui.utils.hexStringToColor

@Composable
fun ClothesInformView(cloth: ClothesInfo, onClick: () -> Unit = {}) {
    Column {
        Column(
            modifier = roundedSquareLargeModifier.padding(vertical = Paddings.large),
        ) {
            ClothInformRow(stringResource(R.string.upper_type), cloth.type)
            ClothInformRow(title = stringResource(R.string.material), content = cloth.material)
            ColorInformRow(
                title = stringResource(R.string.color),
                content = hexStringToColor(cloth.colorCode),
                colorName = cloth.color,
            )
        }
        Spacer(modifier = Modifier.size(Paddings.large))
        if (cloth.isEmptyAdditionalInfo()) {
            TipCard(content = "추가 정보를 입력해\n더 스마트한 의류 관리를 경험해 보세요!") { onClick() }
        } else {
            ClothAdditionalInformView(cloth)
        }
    }
}
