package com.dadada.onecloset.presentation.ui.fitting

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.dadada.onecloset.domain.model.Cloth
import com.dadada.onecloset.presentation.ui.NavigationItem
import com.dadada.onecloset.presentation.ui.common.RowWithTwoButtons
import com.dadada.onecloset.presentation.ui.fitting.component.FittingSelectedClothListView
import com.dadada.onecloset.presentation.ui.theme.BackGround

private const val TAG = "FittingScreen"
@Composable
fun FittingScreen(navHostController: NavHostController) {
    var clothItems = listOf<Cloth>()

    val initialClickedState = List(clothItems.size) { false }
    val itemClickedStateList = remember { initialClickedState.toMutableStateList() }
    Log.d(TAG, "FittingScreen: ${itemClickedStateList.size}")
    val handleItemClick = { newIndex: Int ->
        itemClickedStateList[newIndex] = !itemClickedStateList[newIndex]
    }

    Box(modifier = Modifier.padding(16.dp)) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            FittingSelectedClothListView(modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.size(12.dp))
//            ClothTabGridView(
//                modifier = Modifier,
//                navHostController = navHostController,
//                clothItems = clothItems,
//                itemClickedStateList = itemClickedStateList,
//                onClick = handleItemClick
//            )
        }

        RowWithTwoButtons(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .background(BackGround),
            left = "취소",
            right = "다음",
            onClickLeft = { },
            onClickRight = { navHostController.navigate(NavigationItem.FittingResultNav.route) }
        )
    }
}
