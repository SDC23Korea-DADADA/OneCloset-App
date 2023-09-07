package com.dadada.onecloset.presentation.ui.closet

import android.net.Uri
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ChipDefaults
import androidx.compose.material3.AssistChip
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.dadada.onecloset.presentation.ui.component.ChipEditRow
import com.dadada.onecloset.presentation.ui.component.ClothItemView
import com.dadada.onecloset.presentation.ui.component.DropDownRow
import com.dadada.onecloset.presentation.ui.theme.PrimaryBlack
import com.dadada.onecloset.presentation.ui.theme.Typography

@Composable
fun ClothCreateScreen(photoUri: Uri) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ClothItemView(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(26.dp)), imageUri = photoUri
        ) {

        }
        Spacer(modifier = Modifier.size(16.dp))
        ClothCreateInputView()
    }
}

@Composable
fun ClothCreateInputView() {
    var showType = remember { mutableStateOf(false) }
    var showColor = remember { mutableStateOf(false) }
    var showMaterial = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .clip(RoundedCornerShape(26.dp))
            .background(
                Color.White
            )
    ) {
        Spacer(modifier = Modifier.size(12.dp))
        ChipEditRow("분류", "반팔", reverse = showType)
        ChipEditRow("재질", "면", reverse = showMaterial)
        ChipEditRow("색상", "흰색", reverse = showColor)
    }
}