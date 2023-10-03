package com.dadada.onecloset.presentation.ui.closet.component.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dadada.onecloset.domain.model.Closet
import com.dadada.onecloset.presentation.ui.closet.component.item.ClosetItem
import com.dadada.onecloset.presentation.ui.theme.roundedSquareLargeModifier
import com.dadada.onecloset.presentation.ui.utils.hexStringToColor
import com.dadada.onecloset.presentation.ui.utils.iconHandler
import com.dadada.onecloset.presentation.viewmodel.closet.ClosetViewModel

@Composable
fun ClosetListView(
    closetList: List<Closet>,
    closetViewModel: ClosetViewModel,
    onClick: (Int) -> Unit,
) {
    Box(
        modifier = roundedSquareLargeModifier,
    ) {
        LazyVerticalGrid(
            modifier = Modifier
                .align(Alignment.Center),
            columns = GridCells.Fixed(3),
        ) {
            items(closetList.size) {
                ClosetItem(
                    modifier = Modifier.padding(24.dp),
                    title = closetList[it].name,
                    icon = iconHandler(closetList[it].icon),
                    backGroundTint = hexStringToColor(closetList[it].colorCode),
                    onClick = {
                        closetViewModel.isBasicCloset = it == 0
                        onClick(closetList[it].closetId)
                    },
                )
            }
        }
    }
}
