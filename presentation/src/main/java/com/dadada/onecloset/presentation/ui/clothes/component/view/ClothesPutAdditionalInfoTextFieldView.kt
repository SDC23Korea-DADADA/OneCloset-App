package com.dadada.onecloset.presentation.ui.clothes.component.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.dadada.onecloset.presentation.ui.theme.Paddings
import com.dadada.onecloset.presentation.ui.theme.TextGray
import com.dadada.onecloset.presentation.ui.theme.Typography

@Composable
fun ClothesPutAdditionalInfoTextFieldView(
    title: String,
    hint: String,
    inputState: MutableState<String>,
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = title,
            style = Typography.titleMedium.copy(fontWeight = FontWeight.ExtraBold),
        )
        Spacer(modifier = Modifier.size(Paddings.medium))
        BasicTextField(
            value = inputState.value,
            onValueChange = { inputState.value = it },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),

        ) {
            if (inputState.value.isEmpty()) {
                Text(
                    text = hint,
                    style = Typography.bodyMedium.copy(color = TextGray),
                )
            }
            it()
        }
    }
}
