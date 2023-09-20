package com.dadada.onecloset.presentation.ui.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.dadada.onecloset.presentation.R
import com.dadada.onecloset.presentation.ui.theme.Gray
import com.dadada.onecloset.presentation.ui.theme.PointGreen
import com.dadada.onecloset.presentation.ui.theme.PrimaryBlue
import com.dadada.onecloset.presentation.ui.theme.Typography

@Composable
fun InfoTextView(modifier: Modifier = Modifier, title: String, content: String) {
    Column {
        Text(text = title, style = Typography.titleMedium.copy(fontWeight = FontWeight.ExtraBold))
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = content, style = Typography.bodySmall.copy(color = Gray))
    }
}

@Composable
fun InfoView(
    title: String,
    content: String,
    onClick: () -> Unit,
    iconView: @Composable () -> Unit
) {
    Row(
        modifier = roundedSquareLargeModifier.clickable(onClick = onClick).padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        iconView()
        Spacer(modifier = Modifier.size(12.dp))
        InfoTextView(
            modifier = Modifier,
            title = title,
            content = content
        )
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            modifier = Modifier.size(24.dp),
            imageVector = Icons.Outlined.KeyboardArrowRight,
            tint = Gray,
            contentDescription = "화살표 아이콘"
        )
    }
}

@Composable
fun SelectPhotoView(onClickCamera: () -> Unit, onClickGallery: () -> Unit) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp, top = 8.dp, start = 16.dp, end = 16.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        RoundedSquareIconWithTitleItem(
            modifier = Modifier.size(80.dp),
            title = "카메라",
            icon = R.drawable.ic_camera,
            backGroundTint = PrimaryBlue,
            onClick = { onClickCamera() }
        )

        RoundedSquareIconWithTitleItem(
            modifier = Modifier.size(80.dp),
            title = "갤러리",
            icon = R.drawable.ic_gallery,
            backGroundTint = PointGreen,
            onClick = { onClickGallery() }
        )
    }
}




