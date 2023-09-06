package com.dadada.onecloset.presentation.ui.photo

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.dadada.onecloset.presentation.ui.component.GalleryPhotoItem
import com.dadada.onecloset.presentation.ui.theme.BackGround
import com.dadada.onecloset.presentation.ui.theme.PrimaryBlack
import com.dadada.onecloset.presentation.ui.theme.iconGray
import com.dadada.onecloset.presentation.viewmodel.PhotoViewModel

private const val TAG = "GalleryScreen"

@Composable
fun GalleryScreen(photoViewModel: PhotoViewModel = hiltViewModel()) {
    val pagingPhotos = photoViewModel.photoList.collectAsLazyPagingItems()
    var isCheckedIdx by remember { mutableStateOf(-1) }
    var isCanGo by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        photoViewModel.getPagingPhotos()
        Log.d(TAG, "GalleryScreen: ${photoViewModel.photoList.value}")
    }

    if(isCheckedIdx != -1) {
        isCanGo = true
    }

    Box(modifier = Modifier.fillMaxSize()) {
        LazyVerticalGrid(
            modifier = Modifier,
            columns = GridCells.Fixed(4),
        ) {
            items(pagingPhotos.itemCount) { index ->
                val isCurrentItemChecked = isCheckedIdx == index
                pagingPhotos[index]?.let { galleryImage ->
                    GalleryPhotoItem(url = galleryImage.uri, index, isCurrentItemChecked) {
                        isCheckedIdx = index
                    }
                }
            }
        }

        BottomBox(modifier = Modifier.align(Alignment.BottomCenter), isChecked = isCanGo)
    }
}

@Composable
fun BottomBox(modifier: Modifier, isChecked: Boolean) {
    val boxColor = if (isChecked) PrimaryBlack else BackGround
    val textColor = if (isChecked) Color.White else iconGray

    Box(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(0.08f)
            .graphicsLayer()
            .background(boxColor)
    ) {
        Text(modifier = Modifier.align(Alignment.Center), text = "선택 완료", color = textColor)
    }
}