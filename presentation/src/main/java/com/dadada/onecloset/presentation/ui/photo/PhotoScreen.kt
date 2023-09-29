package com.dadada.onecloset.presentation.ui.photo

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.dadada.onecloset.presentation.ui.theme.Paddings

@Composable
fun PhotoScreen(navHostController: NavHostController, imagePath: String) {
    val decodedPath = Uri.decode(imagePath)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.5f)),
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Paddings.xlarge)
                .clip(RoundedCornerShape(26.dp)),
            model = decodedPath,
            contentDescription = ""
        )

        Icon(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(Paddings.xlarge)
                .clickable { navHostController.popBackStack() },
            imageVector = Icons.Default.ArrowBack,
            contentDescription = ""
        )
    }
}