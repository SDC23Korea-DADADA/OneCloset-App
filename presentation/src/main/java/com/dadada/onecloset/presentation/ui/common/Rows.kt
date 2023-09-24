package com.dadada.onecloset.presentation.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.dadada.onecloset.presentation.ui.theme.PrimaryBlack
import com.dadada.onecloset.presentation.ui.theme.Typography
import com.dadada.onecloset.presentation.ui.theme.Gray
import com.dadada.onecloset.presentation.ui.theme.Paddings

@Composable
fun DropDownRow(modifier: Modifier = Modifier, component: @Composable () -> Unit, reverse: Boolean, onClick: () -> Unit) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        component()
        DropDownButton(reverse = reverse) {
            onClick()
        }
    }
}

@Composable
fun ChipEditRow(title: String, content: String, reverse: MutableState<Boolean>) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            modifier = Modifier.padding(start = 8.dp),
            text = title,
            style = Typography.titleMedium
        )
        DropDownRow(
            component = {
                SuggestionChip(
                    onClick = { /*TODO*/ },
                    label = { Text(text = content, color = Color.White) },
                    colors = SuggestionChipDefaults.suggestionChipColors(containerColor = PrimaryBlack)
                )
            },
            reverse = reverse.value,
            onClick = { reverse.value = !reverse.value }
        )
    }
}

@Composable
fun ColorEditRow(title: String, content: Color, reverse: MutableState<Boolean>) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            modifier = Modifier.padding(start = 8.dp),
            text = title,
            style = Typography.titleMedium
        )
        DropDownRow(
            component = {
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(36.dp)
                        .background(content)
                        .border(1.dp, Color.Black, CircleShape)
                )
            },
            reverse = reverse.value,
            onClick = { reverse.value = !reverse.value }
        )
    }
}

@Composable
fun ColorInformRow(title: String, content: Color) {
    Row(
        modifier = screenModifier.padding(vertical = Paddings.small),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            modifier = Modifier.padding(start = Paddings.medium),
            text = title,
            style = Typography.titleMedium.copy(fontWeight = FontWeight.ExtraBold)
        )
        Box(
            modifier = Modifier
                .shadow(3.dp, CircleShape)
                .clip(CircleShape)
                .size(36.dp)
                .background(content)
                //.border(1.dp, PrimaryBlack, CircleShape)
        )
    }
}

@Composable
fun ClothInformRow(title: String, content: String) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            modifier = Modifier.padding(start = 8.dp),
            text = title,
            style = Typography.titleMedium.copy(fontWeight = FontWeight.ExtraBold)
        )
        SuggestionChip(
            onClick = { },
            label = { Text(text = content, color = Color.White) },
            colors = SuggestionChipDefaults.suggestionChipColors(containerColor = PrimaryBlack)
        )
    }
}


@Composable
fun RowWithTwoButtons(
    modifier: Modifier = Modifier,
    left: String,
    right: String,
    onClickLeft: () -> Unit,
    onClickRight: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding()
    ) {
        Text(modifier = Modifier
            .clickable { onClickLeft() }
            .weight(1f)
            .padding(vertical = 12.dp),
            text = left,
            style = Typography.titleLarge.copy(fontWeight = FontWeight.ExtraBold),
            textAlign = TextAlign.Center)
        Text(modifier = Modifier
            .clickable { onClickRight() }
            .weight(1f)
            .padding(vertical = 12.dp),
            text = right,
            style = Typography.titleLarge.copy(fontWeight = FontWeight.ExtraBold),
            textAlign = TextAlign.Center)
    }
}

@Composable
fun RowWithSmallTwoButtons(
    modifier: Modifier = Modifier,
    left: String,
    right: String,
    onClickLeft: () -> Unit,
    onClickRight: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding()
    ) {
        Text(modifier = Modifier
            .clickable { onClickLeft() }
            .weight(1f)
            .padding(vertical = 12.dp),
            text = left,
            fontWeight = FontWeight.ExtraBold,
            textAlign = TextAlign.Center)
        Text(modifier = Modifier
            .clickable { onClickRight() }
            .weight(1f)
            .padding(vertical = 12.dp),
            text = right,
            fontWeight = FontWeight.ExtraBold,
            textAlign = TextAlign.Center)
    }
}

@Composable
fun ClickableRow(id: Int, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = stringResource(id = id))
        Icon(
            imageVector = Icons.Filled.KeyboardArrowRight,
            contentDescription = "",
            tint = Color.LightGray
        )
    }
}

@Composable
fun LicenseRow(content: String, version: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = content)
        Text(
            modifier = Modifier.padding(end = 4.dp),
            text = version,
            style = Typography.titleSmall.copy(Gray)
        )
    }
}

