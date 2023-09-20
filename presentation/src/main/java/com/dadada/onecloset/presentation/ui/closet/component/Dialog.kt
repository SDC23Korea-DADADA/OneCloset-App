package com.dadada.onecloset.presentation.ui.closet.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.dadada.onecloset.presentation.ui.common.RowWithSmallTwoButtons
import com.dadada.onecloset.presentation.ui.common.RowWithTwoButtons
import com.dadada.onecloset.presentation.ui.common.roundedSquareLargeModifier
import com.dadada.onecloset.presentation.ui.theme.BackGround
import com.dadada.onecloset.presentation.ui.theme.Gray
import com.dadada.onecloset.presentation.ui.theme.Paddings
import com.dadada.onecloset.presentation.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClothInputAdditionalInformDialog(
    onDismissRequest: () -> Unit,
    onConfirmRequest: () -> Unit
) {

    AlertDialog(modifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(26.dp))
        .background(BackGround),
        onDismissRequest = { onDismissRequest() }) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(26.dp))
                .background(BackGround)
                .padding(Paddings.xlarge),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "추가 정보 입력",
                style = Typography.titleMedium.copy(fontWeight = FontWeight.ExtraBold),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.size(Paddings.large))
            ClothInputAdditionalInformDialogView()
            Text(
                modifier = Modifier.padding(vertical = Paddings.medium),
                text = "*추가 정보를 입력하면 더 다양한 서비스를 이용할 수 있어요!",
                style = Typography.titleSmall.copy(color = Gray)
            )
            RowWithSmallTwoButtons(left = "취소",
                right = "등록하기",
                onClickLeft = { onDismissRequest() }) {
                onConfirmRequest()
            }
        }
    }
}