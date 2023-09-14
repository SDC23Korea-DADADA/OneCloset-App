package com.dadada.onecloset.presentation.ui.account

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.dadada.onecloset.presentation.ui.account.component.AccountText
import com.dadada.onecloset.presentation.ui.common.CircleImageView
import com.dadada.onecloset.presentation.ui.common.LicenseRow
import com.dadada.onecloset.presentation.ui.common.ListRoundedSquare
import com.dadada.onecloset.presentation.ui.common.RoundedSquare
import com.dadada.onecloset.presentation.ui.common.screenModifier
import com.dadada.onecloset.presentation.ui.theme.Gray
import com.dadada.onecloset.presentation.ui.theme.Typography

@Composable
fun MyPageScreen() {
    val personalInfoContents = AccountText.personalInfoContents
    val appInfoContents = AccountText.appInfoContents
    Column(
        modifier = screenModifier
            .wrapContentHeight()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        CircleImageView(modifier = Modifier.size(120.dp), url = "")

        Spacer(modifier = Modifier.size(36.dp))

        Column {
            SmallTitle(id = AccountText.ACCOUNT.id)
            RoundedSquare(title = "카카오 계정", content = "juyong4190@gmail.com")
        }

        Spacer(modifier = Modifier.size(16.dp))
        Column {
            SmallTitle(id = AccountText.MODEL.id)
            RoundedSquare(title = "성별", content = "남성")
        }
        Spacer(modifier = Modifier.size(16.dp))

        Column {
            SmallTitle(id = AccountText.PERSONAL.id)
            ListRoundedSquare(list = personalInfoContents)
        }

        Spacer(modifier = Modifier.size(16.dp))

        Column {
            SmallTitle(id = AccountText.APP.id)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(26.dp))
                    .background(Color.White)
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                LicenseRow(content = "버전", version = "1.0.0")
//                ClickableRow(content = "라이선스") {
//
                //}
            }
        }
    }
}

@Composable
fun SmallTitle(id: Int) = Text(
    modifier = Modifier.padding(start = 16.dp, top = 4.dp, bottom = 4.dp),
    text = stringResource(id = id),
    style = Typography.titleSmall.copy(color = Gray),
)