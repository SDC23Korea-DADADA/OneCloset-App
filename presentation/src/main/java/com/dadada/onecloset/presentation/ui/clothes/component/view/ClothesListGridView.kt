package com.dadada.onecloset.presentation.ui.clothes.component.view

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import com.dadada.onecloset.domain.model.clothes.ClothesInfo
import com.dadada.onecloset.presentation.R
import com.dadada.onecloset.presentation.ui.components.RoundedSquareImageItem
import com.dadada.onecloset.presentation.ui.theme.roundedSquareMediumModifier

@Composable
fun ClothesListGridView(
    clothItems: List<ClothesInfo>,
    icon: Int? = null,
    itemClickedStateList: List<Boolean> = mutableStateListOf(),
    onClick: (Int) -> Unit = {},
) {
    var curIcon: Int? = icon

    LazyVerticalGrid(
        modifier = Modifier.padding(horizontal = 4.dp),
        columns = GridCells.Fixed(3),
    ) {
        items(clothItems.size) { it ->
            if (itemClickedStateList.isNotEmpty()) {
                curIcon =
                    if (itemClickedStateList[it]) R.drawable.ic_checked else R.drawable.ic_unchecked
            }
            RoundedSquareImageItem(
                modifier = roundedSquareMediumModifier,
                imageUri = clothItems[it].thumnailUrl.toUri(),
                icon = curIcon,
                onClick = { onClick(clothItems[it].clothesId) },
            )
        }
    }
}
