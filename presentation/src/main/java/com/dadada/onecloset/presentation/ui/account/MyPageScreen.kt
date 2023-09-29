package com.dadada.onecloset.presentation.ui.account

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.dadada.onecloset.presentation.ui.NavigationItem
import com.dadada.onecloset.presentation.ui.account.component.AccountMultiLineSection
import com.dadada.onecloset.presentation.ui.account.component.AccountSingleLineSection
import com.dadada.onecloset.presentation.ui.account.component.AccountText
import com.dadada.onecloset.presentation.ui.common.CircleImageView
import com.dadada.onecloset.presentation.ui.common.TwoButtonDialog
import com.dadada.onecloset.presentation.ui.common.screenModifier
import com.dadada.onecloset.presentation.ui.theme.Paddings
import com.dadada.onecloset.presentation.ui.utils.Mode
import com.dadada.onecloset.presentation.ui.utils.NetworkResultHandler
import com.dadada.onecloset.presentation.ui.utils.PermissionRequester
import com.dadada.onecloset.presentation.ui.utils.Permissions
import com.dadada.onecloset.presentation.ui.utils.ShowToast
import com.dadada.onecloset.presentation.viewmodel.MainViewModel
import com.dadada.onecloset.presentation.viewmodel.PhotoViewModel
import com.dadada.onecloset.presentation.viewmodel.account.AccountViewModel

@Composable
fun MyPageScreen(
    navHostController: NavHostController,
    mainViewModel: MainViewModel,
    photoViewModel: PhotoViewModel,
    accountViewModel: AccountViewModel = hiltViewModel(),
) {
    val personalInfoContents = AccountText.personalInfoContents
    val appInfoContents = AccountText.appInfoContents
    val appModelContents = AccountText.appModelContents
    val accountInfo by accountViewModel.accountInfo.collectAsState()
    val leaveUser by accountViewModel.leaveUserState.collectAsState()

    NetworkResultHandler(state = leaveUser, mainViewModel = mainViewModel) {
        navHostController.navigate(NavigationItem.LogInNav.route)
    }

    var clickCourse by remember { mutableStateOf(false) }
    if (clickCourse) {
        PermissionRequester(
            permission = Permissions.readExternalStoragePermission,
            onDismissRequest = { clickCourse = !clickCourse },
            onPermissionGranted = { navHostController.navigate(NavigationItem.GalleryNav.route) }) {
            clickCourse = !clickCourse
        }
    }

    var showDialog by remember { mutableStateOf(false) }
    if(showDialog) {
        TwoButtonDialog(
            onDismissRequest = { showDialog = !showDialog },
            onConfirmation = { accountViewModel.leaveUser() },
            dialogTitle = "One Closet 탈퇴",
            dialogText = "One Closet을 탈퇴하면 데이터는 즉시 삭제되며 되돌릴 수 없습니다. 정말 떠나시겠어요?"
        )
    }

    val onClickModelRegister = {
        photoViewModel.curMode = Mode.model
        clickCourse = !clickCourse
    }
    val onClickModel = listOf(onClickModelRegister)

    val onClickPermission = {}
    val onClickLogout = {
        accountViewModel.signOut()
        navHostController.navigate(NavigationItem.LogInNav.route)
    }
    val onClickWithdrawal = {
        showDialog = !showDialog
        Unit
    }
    val onClickPersonal = listOf(onClickPermission, onClickLogout, onClickWithdrawal)

    val onClickVersion = {}
    val onClickLicense = {}
    val onClickAppInfo = listOf(onClickVersion, onClickLicense)

    Column(
        modifier = screenModifier
            .wrapContentHeight()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        accountInfo?.let {
            CircleImageView(
                modifier = Modifier.size(120.dp),
                url = it.profileImg
            )
        }

        accountInfo?.let {
            AccountSingleLineSection(
                modifier = Modifier.padding(vertical = Paddings.small),
                title = stringResource(id = AccountText.ACCOUNT.id),
                subTitle = it.social,
                content = it.email
            )
        }


        AccountMultiLineSection(
            modifier = Modifier.padding(vertical = Paddings.small),
            title = stringResource(id = AccountText.MODEL.id),
            subTitleList = appModelContents,
            onClickList = onClickModel
        )

        AccountMultiLineSection(
            modifier = Modifier.padding(vertical = Paddings.small),
            title = stringResource(id = AccountText.PERSONAL.id),
            subTitleList = personalInfoContents,
            onClickList = onClickPersonal
        )

        AccountMultiLineSection(
            modifier = Modifier.padding(vertical = Paddings.small),
            title = stringResource(id = AccountText.APP.id),
            subTitleList = appInfoContents,
            onClickList = onClickAppInfo
        )
    }
}
