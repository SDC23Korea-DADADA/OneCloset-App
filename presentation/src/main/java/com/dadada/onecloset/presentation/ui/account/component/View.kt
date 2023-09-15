package com.dadada.onecloset.presentation.ui.account.component

import androidx.compose.foundation.Image
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.dadada.onecloset.presentation.ui.account.model.SignInButton
import com.dadada.onecloset.presentation.ui.common.roundedSquareLargeModifier
import com.dadada.onecloset.presentation.ui.theme.Gray
import com.dadada.onecloset.presentation.ui.theme.Paddings
import com.dadada.onecloset.presentation.ui.theme.Typography

@Composable
fun AccountTitleView(title: String) {
    Text(
        modifier = Modifier.padding(
            start = Paddings.xlarge,
            top = Paddings.small,
            bottom = Paddings.small
        ),
        text = title,
        style = Typography.titleSmall.copy(color = Gray),
    )
}


@Composable
fun SignInButtonView(signInButtons: List<SignInButton>) {
    signInButtons.forEach {
        Image(
            modifier = Modifier
                .padding(horizontal = Paddings.xlarge)
                .clickable(onClick = it.action),
            painter = painterResource(id = it.id),
            contentDescription = it.description
        )
        Spacer(modifier = Modifier.size(12.dp))
    }
}

@Composable
fun AccountSingleLineSection(
    modifier: Modifier = Modifier,
    title: String,
    subTitle: String,
    content: String
) {
    Column(modifier = modifier) {
        AccountTitleView(title = title)
        Column(modifier = roundedSquareLargeModifier.padding(Paddings.xlarge)) {
            Text(text = subTitle, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.size(Paddings.small))
            Text(text = content, color = Color.DarkGray)
        }
    }
}

@Composable
fun AccountMultiLineSection(modifier: Modifier = Modifier, title: String, subTitleList: List<Int>) {
    Column(modifier = modifier) {
        AccountTitleView(title = title)

        Column(modifier = roundedSquareLargeModifier.padding(Paddings.xlarge)) {
            subTitleList.forEach {
                Row(modifier = Modifier.padding(vertical = Paddings.medium),verticalAlignment = Alignment.CenterVertically) {
                    Text(text = stringResource(id = it))
                    Spacer(modifier = Modifier.weight(1f))
                    if (it == AccountText.VERSION.id) {
                        Text(text = "1.0.0", color = Color.LightGray)
                    } else {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowRight,
                            contentDescription = "",
                            tint = Color.LightGray
                        )
                    }
                }
            }
        }
    }
}