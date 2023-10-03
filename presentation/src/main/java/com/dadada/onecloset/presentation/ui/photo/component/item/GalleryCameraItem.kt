package com.dadada.onecloset.presentation.ui.photo.component.item

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.dadada.onecloset.presentation.R
import com.dadada.onecloset.presentation.ui.theme.Green

@Composable
fun GalleryCameraItem(onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .background(Color.White)
            .aspectRatio(1f)
            .border(1.dp, Color.White)
            .clickable {
                onClick()
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Icon(
            modifier = Modifier
                .padding(4.dp),
            painter = painterResource(id = R.drawable.ic_camera),
            tint = Green,
            contentDescription = "Checked",
        )
        Text(text = "사진 찍기")
    }
}
