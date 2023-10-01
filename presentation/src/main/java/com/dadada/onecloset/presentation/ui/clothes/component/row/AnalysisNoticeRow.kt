package com.dadada.onecloset.presentation.ui.clothes.component.row

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.dadada.onecloset.presentation.ui.theme.Size
import com.dadada.onecloset.presentation.ui.theme.TextGray
import com.dadada.onecloset.presentation.ui.theme.Typography

@Composable
fun AnalysisNoticeRow(modifier: Modifier = Modifier) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Icon(
            modifier = Modifier.size(Size.large),
            imageVector = Icons.Outlined.Info,
            contentDescription = "info",
            tint = TextGray,
        )
        Spacer(modifier = Modifier.size(Size.xsmall))
        Text(
            text = "분석 결과가 정확한가요? 버튼을 클릭하면 수정할 수 있어요!",
            style = Typography.bodySmall.copy(color = TextGray),
        )
    }
}
