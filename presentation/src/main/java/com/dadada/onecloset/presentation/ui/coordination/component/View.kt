package com.dadada.onecloset.presentation.ui.coordination.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dadada.onecloset.presentation.R
import com.dadada.onecloset.presentation.ui.common.RoundedSquareIconWithTitleItem
import com.dadada.onecloset.presentation.ui.theme.PointGreen
import com.dadada.onecloset.presentation.ui.theme.PrimaryBlue

@Composable
fun SelectCodiView(onClickRecord: () -> Unit, onClickPlan: () -> Unit) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp, top = 8.dp, start = 16.dp, end = 16.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        RoundedSquareIconWithTitleItem(
            modifier = Modifier.size(80.dp),
            title = "기록",
            icon = R.drawable.ic_daily,
            backGroundTint = PrimaryBlue,
            onClick = onClickRecord
        )

        RoundedSquareIconWithTitleItem(
            modifier = Modifier.size(80.dp),
            title = "계획",
            icon = R.drawable.ic_lamp,
            backGroundTint = PointGreen,
            onClick = onClickPlan
        )
    }
}