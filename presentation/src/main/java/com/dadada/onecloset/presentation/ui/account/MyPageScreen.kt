package com.dadada.onecloset.presentation.ui.account

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.dadada.onecloset.presentation.ui.NavigationItem
import com.dadada.onecloset.presentation.ui.account.component.AccountMultiLineSection
import com.dadada.onecloset.presentation.ui.account.component.AccountSingleLineSection
import com.dadada.onecloset.presentation.ui.account.component.AccountText
import com.dadada.onecloset.presentation.ui.account.component.FittingModelListBottomSheet
import com.dadada.onecloset.presentation.ui.common.CircleImageView
import com.dadada.onecloset.presentation.ui.common.screenModifier
import com.dadada.onecloset.presentation.ui.theme.Paddings
import com.dadada.onecloset.presentation.viewmodel.MainViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyPageScreen(navHostController: NavHostController, mainViewModel: MainViewModel) {
    val personalInfoContents = AccountText.personalInfoContents
    val appInfoContents = AccountText.appInfoContents

    Column(
        modifier = screenModifier
            .wrapContentHeight()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        CircleImageView(modifier = Modifier.size(120.dp), url = "")

        AccountSingleLineSection(
            modifier = Modifier.padding(vertical = Paddings.small),
            title = stringResource(id = AccountText.ACCOUNT.id),
            subTitle = "카카오 계정",
            content = "juyong4190@gmail.com"
        )

        AccountSingleLineSection(
            modifier = Modifier.padding(vertical = Paddings.small),
            title = stringResource(id = AccountText.MODEL.id),
            subTitle = stringResource(id = AccountText.GENDER.id),
            content = "남자",
            onClick = {  }
        )

        AccountMultiLineSection(
            modifier = Modifier.padding(vertical = Paddings.small),
            title = stringResource(id = AccountText.PERSONAL.id),
            subTitleList = personalInfoContents,
        )

        AccountMultiLineSection(
            modifier = Modifier.padding(vertical = Paddings.small),
            title = stringResource(id = AccountText.APP.id),
            subTitleList = appInfoContents,
        )
    }
}
