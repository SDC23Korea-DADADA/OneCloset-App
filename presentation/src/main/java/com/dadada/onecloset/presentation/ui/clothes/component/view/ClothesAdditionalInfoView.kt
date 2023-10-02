package com.dadada.onecloset.presentation.ui.clothes.component.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.dadada.onecloset.domain.model.clothes.ClothesInfo
import com.dadada.onecloset.presentation.ui.clothes.component.row.ClothesAdditionalInfoRow
import com.dadada.onecloset.presentation.ui.theme.roundedSquareLargeModifier
import com.dadada.onecloset.presentation.ui.theme.Paddings

@Composable
fun ClothesAdditionalInformView(clothesInfo: ClothesInfo) {
    Column(modifier = roundedSquareLargeModifier.padding(Paddings.large)) {
        Column(modifier = Modifier.padding(Paddings.large)) {
            ClothesAdditionalInfoRow(title = "설명")
            Text(text = clothesInfo.description)
            ClothesAdditionalInfoRow(title = "해쉬태그", clothesInfo.hashtagList)
            ClothesAdditionalInfoRow(title = "날씨", clothesInfo.weatherList)
            ClothesAdditionalInfoRow(title = "TPO", clothesInfo.tpoList)
        }
    }
}
