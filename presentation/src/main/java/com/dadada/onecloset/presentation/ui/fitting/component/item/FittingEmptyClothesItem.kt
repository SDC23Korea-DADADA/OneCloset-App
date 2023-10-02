package com.dadada.onecloset.presentation.ui.fitting.component.item

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.dadada.onecloset.presentation.ui.theme.BackGround
import com.dadada.onecloset.presentation.ui.theme.Typography
import com.dadada.onecloset.presentation.ui.theme.roundedSquareSmallModifier

@Composable
fun FittingEmptyClothesItem(icon: Int, content: String) {
    Column(
        modifier = roundedSquareSmallModifier
            .size(100.dp)
            .background(BackGround),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(painter = painterResource(id = icon), contentDescription = "", tint = Color.LightGray)
        Spacer(modifier = Modifier.size(4.dp))
        Text(text = content, style = Typography.bodySmall.copy(color = Color.LightGray))
    }
}