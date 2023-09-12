package com.dadada.onecloset.presentation.ui.fitting.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import com.dadada.onecloset.domain.model.Cloth
import com.dadada.onecloset.presentation.ui.common.ClothItemView

@Composable
fun FittingSelectedClothListView(modifier: Modifier = Modifier) {
    val list = listOf(Cloth(), Cloth(), Cloth())
    Row(modifier = modifier, horizontalArrangement = Arrangement.SpaceAround) {
        list.forEach {
            ClothItemView(
                modifier = Modifier
                    .padding(16.dp)
                    .weight(1f)
                    .clip(RoundedCornerShape(20.dp)),
                imageUri = it.url.toUri()
            ) {

            }
        }
    }
}