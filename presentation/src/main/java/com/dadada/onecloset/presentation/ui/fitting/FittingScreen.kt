package com.dadada.onecloset.presentation.ui.fitting

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.dadada.onecloset.presentation.ui.closet.component.ClothTabGridView
import com.dadada.onecloset.presentation.ui.component.RowWithTwoButtons
import com.dadada.onecloset.presentation.ui.fitting.component.FittingSelectedClothListView

@Composable
fun FittingScreen(navHostController: NavHostController) {
    Box(modifier = Modifier.padding(16.dp)){
        FittingSelectedClothListView()
        ClothTabGridView(
            modifier = Modifier,
            navHostController = navHostController
        )

        RowWithTwoButtons(modifier = Modifier.align(Alignment.BottomCenter),left = "취소", right = "다음", onClickLeft = { /*TODO*/ }) {
            
        }
    }
}
