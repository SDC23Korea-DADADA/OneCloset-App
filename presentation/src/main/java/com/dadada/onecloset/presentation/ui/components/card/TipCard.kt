package com.dadada.onecloset.presentation.ui.components.card

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.dadada.onecloset.presentation.R
import com.dadada.onecloset.presentation.ui.theme.BackGroundGray
import com.dadada.onecloset.presentation.ui.theme.Paddings
import com.dadada.onecloset.presentation.ui.theme.Size
import com.dadada.onecloset.presentation.ui.theme.TextGray
import com.dadada.onecloset.presentation.ui.theme.Typography
import com.dadada.onecloset.presentation.ui.theme.roundedSquareLargeModifier

@Composable
fun TipCard(modifier: Modifier = roundedSquareLargeModifier, content: String, isClickable: Boolean = true, onClick: () -> Unit) {
    Row(
        modifier = modifier
            .background(BackGroundGray)
            .then(if (isClickable) Modifier.clickable { onClick() } else Modifier)
            .padding(Paddings.large),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(horizontalAlignment = Alignment.Start) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    modifier = Modifier.size(Size.xlarge),
                    painter = painterResource(id = R.drawable.ic_lamp),
                    contentDescription = "",
                    tint = TextGray,
                )
                Text(
                    text = "Tip.",
                    style = Typography.bodyMedium.copy(
                        color = TextGray,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 14.sp,
                    ),
                )
            }
            Spacer(modifier = Modifier.size(Size.xsmall))
            Text(
                modifier = Modifier.padding(start = Paddings.medium),
                text = content,
                style = Typography.bodySmall.copy(
                    color = TextGray,
                    fontWeight = FontWeight.Bold,
                ),
            )
        }
        if (isClickable) {
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = "",
                tint = TextGray,
            )
        }
    }
}
