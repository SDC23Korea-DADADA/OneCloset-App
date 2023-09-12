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
import androidx.compose.ui.unit.dp
import com.dadada.onecloset.presentation.ui.common.CircleImageView
import com.dadada.onecloset.presentation.ui.common.ClickableRow
import com.dadada.onecloset.presentation.ui.common.LicenseRow
import com.dadada.onecloset.presentation.ui.common.ListRoundedShape
import com.dadada.onecloset.presentation.ui.common.RoundedShape
import com.dadada.onecloset.presentation.ui.theme.Typography
import com.dadada.onecloset.presentation.ui.theme.Gray

@Composable
fun MyPageScreen() {
    val list = listOf("이 앱이 사용하는 권한", "One Cloest 로그아웃", "One Closet 탈퇴")
    val list2 = listOf("버전 정보", "라이선스")
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        CircleImageView(modifier = Modifier.size(120.dp), url = "")
        Spacer(modifier = Modifier.size(36.dp))
        Column {
            SmallTitle(title = "계정")
            RoundedShape(title = "카카오 계정", content = "juyong4190@gmail.com")
        }
        Spacer(modifier = Modifier.size(16.dp))
        Column {
            SmallTitle(title = "가상모델")
            RoundedShape(title = "성별", content = "남성")
        }
        Spacer(modifier = Modifier.size(16.dp))

        Column {
            SmallTitle(title = "개인정보")
            ListRoundedShape(list = list)
        }

        Spacer(modifier = Modifier.size(16.dp))

        Column {
            SmallTitle(title = "앱 정보")
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(26.dp))
                    .background(Color.White)
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                LicenseRow(content = "버전", version = "1.0.0")
                ClickableRow(content = "라이선스") {
                    
                }
            }
        }
    }
}

@Composable
fun SmallTitle(title: String) = Text(
    modifier = Modifier.padding(start = 16.dp, top = 4.dp, bottom = 4.dp),
    text = title,
    style = Typography.titleSmall.copy(color = Gray),
)