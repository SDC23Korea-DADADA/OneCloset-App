package com.dadada.onecloset.presentation.ui.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.dadada.onecloset.presentation.ui.theme.Typography

@Composable
fun BottomSheetAddCloset() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "옷장 등록",
            style = Typography.titleMedium.copy(fontWeight = FontWeight.ExtraBold),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.size(16.dp))
        Row {
            Text(text = "대표 아이콘", style = Typography.titleSmall.copy(fontWeight = FontWeight.Bold))
        }
        Spacer(modifier = Modifier.size(12.dp))
        Row {
            Text(text = "이름", style = Typography.titleSmall.copy(fontWeight = FontWeight.Bold))
        }
        Spacer(modifier = Modifier.size(16.dp))
        Button(modifier = Modifier.fillMaxWidth(), onClick = { /*TODO*/ }) {
            Text(text = "등록")
        }
    }
}