package com.dadada.onecloset.presentation.ui.clothes.component.row

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.dadada.onecloset.presentation.ui.theme.roundedSquareLargeModifier
import com.dadada.onecloset.presentation.ui.theme.Paddings
import com.dadada.onecloset.presentation.ui.theme.Size
import com.dadada.onecloset.presentation.ui.theme.Typography

@Composable
fun ClothesCourseInfoRow(title: String, content: String) {
    Column(modifier = roundedSquareLargeModifier.padding(Paddings.xlarge)) {
        Text(text = title, style = Typography.titleSmall)
        Spacer(modifier = Modifier.size(Size.small))
        Text(text = content, style = Typography.bodyMedium)
    }
}
