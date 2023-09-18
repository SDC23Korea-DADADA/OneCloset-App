package com.dadada.onecloset.presentation.ui.fitting.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import com.dadada.onecloset.domain.model.Cloth
import com.dadada.onecloset.presentation.R
import com.dadada.onecloset.presentation.ui.common.RoundedSquareImageItem
import com.dadada.onecloset.presentation.ui.common.roundedSquareMediumModifier

@Composable
fun FittingSelectedClothListView(modifier: Modifier = Modifier) {
    val list = listOf<Cloth>()
    Row(modifier = modifier, horizontalArrangement = Arrangement.SpaceAround) {
        list.forEach {
            RoundedSquareImageItem(
                modifier = roundedSquareMediumModifier.size(80.dp),
                imageUri = it.url.toUri(),
                icon = R.drawable.ic_close
            ) {

            }
        }
    }
}