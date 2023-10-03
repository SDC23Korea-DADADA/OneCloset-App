package com.dadada.onecloset.presentation.ui.clothes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.core.net.toUri
import androidx.navigation.NavHostController
import com.dadada.onecloset.presentation.ui.NavigationItem
import com.dadada.onecloset.presentation.ui.clothes.component.view.ClothesAnalysisView
import com.dadada.onecloset.presentation.ui.common.item.BasicImageItem
import com.dadada.onecloset.presentation.ui.common.card.TipCard
import com.dadada.onecloset.presentation.ui.theme.roundedSquareLargeModifier
import com.dadada.onecloset.presentation.ui.common.row.TwoButtonRow
import com.dadada.onecloset.presentation.ui.theme.screenModifier
import com.dadada.onecloset.presentation.ui.theme.Paddings
import com.dadada.onecloset.presentation.ui.theme.Size
import com.dadada.onecloset.presentation.ui.utils.NetworkResultHandler
import com.dadada.onecloset.presentation.viewmodel.MainViewModel
import com.dadada.onecloset.presentation.viewmodel.closet.ClosetViewModel

@Composable
fun ClothesAnalysisScreen(
    navHostController: NavHostController,
    mainViewModel: MainViewModel,
    closetViewModel: ClosetViewModel,
) {
    val clothCareCourseState by closetViewModel.clothCareCourseState.collectAsState()
    NetworkResultHandler(state = clothCareCourseState, mainViewModel = mainViewModel) {
        closetViewModel.clothesInfo.laundry = it.laundry
        closetViewModel.clothesInfo.dryer = it.dryer
        closetViewModel.clothesInfo.airDressor = it.airDresser
        closetViewModel.resetNetworkStates()
        navHostController.navigate(NavigationItem.ClothCourseNav.route)
    }

    Column(
        modifier = screenModifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        BasicImageItem(
            modifier = roundedSquareLargeModifier,
            imageUri = closetViewModel.clothesInfo.image.toUri(),
            icon = null,
        ) {
        }

        Spacer(modifier = Modifier.size(Size.large))
        ClothesAnalysisView(
            modifier = roundedSquareLargeModifier.padding(Paddings.small),
            clothesInfo = closetViewModel.clothesInfo,
        )
        Spacer(modifier = Modifier.size(Size.large))
        TipCard(
            content = "분석 결과가 정확한가요? 버튼을 클릭하면 수정할 수 있어요.\n수정된 정보들은 더 정확한 분석에 사용돼요!",
            isClickable = false,
        ) {
        }

        Spacer(modifier = Modifier.weight(1f))
        TwoButtonRow(left = "취소", right = "다음", onClickLeft = {
            closetViewModel.resetNetworkStates()
            navHostController.popBackStack()
        }) {
            closetViewModel.getClothCare()
        }
    }
}
