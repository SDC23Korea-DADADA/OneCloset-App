package com.dadada.onecloset.presentation.ui.closet

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.dadada.onecloset.presentation.R
import com.dadada.onecloset.presentation.ui.common.SmallRoundedShape

@Composable
fun ClosetScreen() {
    Scaffold(

    ) {
        Column(modifier = Modifier.padding(it).fillMaxSize()) {
            SmallRoundedShape(
                title = "데일리코디",
                content = "매일 코디를 기획하고 일상을 기록해요.",
                icon = R.drawable.ic_date
            ) {

            }
            Spacer(modifier = Modifier.size(16.dp))
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(26.dp))
                    .background(Color.White)
                    .padding(16.dp)
            ) {

            }
        }
    }
}