package com.dadada.onecloset.presentation.ui.fitting.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.core.net.toUri
import com.dadada.onecloset.domain.model.Cloth
import com.dadada.onecloset.presentation.ui.common.DropDownRow
import com.dadada.onecloset.presentation.ui.common.FittingDropDownMenu
import com.dadada.onecloset.presentation.ui.common.roundedSquareLargeModifier
import com.dadada.onecloset.presentation.ui.theme.Paddings
import com.dadada.onecloset.presentation.ui.theme.Typography
import com.dadada.onecloset.presentation.ui.utils.FittingEmptyItem

private const val TAG = "View"

@Composable
fun FittingSelectedClothListView(
    modifier: Modifier = Modifier,
    clothList: List<Cloth>,
    modeIdx: Int,
    emptyItemList: List<FittingEmptyItem>,
    selectedItemList: List<Cloth>,
    onClickDropDown: (Int) -> Unit,
) {
    var modeClick by remember { mutableStateOf(false) }
    val modeTitleList = listOf<String>("상하의", "상의", "하의", "한벌옷")

    Column(
        modifier = roundedSquareLargeModifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(modifier = Modifier.align(Alignment.End)) {
            DropDownRow(
                component = {
                    Text(
                        text = modeTitleList[modeIdx],
                        style = Typography.titleMedium.copy(fontWeight = FontWeight.ExtraBold)
                    )
                },
                reverse = modeClick,
                onClick = { modeClick = !modeClick }
            )
            if (modeClick) {
                FittingDropDownMenu(
                    expanded = modeClick,
                    modeTitleList = modeTitleList,
                    onClick = { onClickDropDown(it) }) {
                    modeClick = !modeClick
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Paddings.large),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            selectedItemList.forEachIndexed { index, cloth ->
                if (cloth.clothesId == -1) {
                    EmptyClothItem(
                        icon = emptyItemList[index].icon,
                        content = emptyItemList[index].content
                    )
                } else {
                    SelectClothItem(imageUri = cloth.thumnailUrl.toUri(), onClick = {})
                }
            }
        }
    }
}

@Composable
fun OnlyTopView() {

}
