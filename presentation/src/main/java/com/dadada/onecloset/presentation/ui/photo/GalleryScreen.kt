package com.dadada.onecloset.presentation.ui.photo

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.dadada.onecloset.domain.model.Photo
import com.dadada.onecloset.presentation.ui.ClothAnalysisNav
import com.dadada.onecloset.presentation.ui.common.GalleryPhotoItem
import com.dadada.onecloset.presentation.ui.theme.PrimaryBlack
import com.dadada.onecloset.presentation.ui.theme.Gray
import com.dadada.onecloset.presentation.viewmodel.PhotoViewModel

private const val TAG = "GalleryScreen"

@Composable
fun GalleryScreen(
    navController: NavHostController,
    photoViewModel: PhotoViewModel = hiltViewModel()
) {
    val pagingPhotos = photoViewModel.photoList.collectAsLazyPagingItems()
    val isCheckedIdx = photoViewModel.isCheckedIdx.collectAsState()

    LaunchedEffect(Unit) {
        photoViewModel.getPagingPhotos()
    }

    Scaffold(
        topBar = {
            GalleryHeader(
                navController = navController,
                pagingPhotos = pagingPhotos,
                isCheckedIdx = isCheckedIdx
            )
        }
    ) {
        LazyVerticalGrid(
            modifier = Modifier.padding(it),
            columns = GridCells.Fixed(4),
        ) {
            items(pagingPhotos.itemCount) { index ->
                val isCurrentItemChecked = isCheckedIdx.value == index
                pagingPhotos[index]?.let { galleryImage ->
                    GalleryPhotoItem(url = galleryImage.uri, index, isCurrentItemChecked) {
                        photoViewModel.setCheckedIndex(index)
                    }
                }
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GalleryHeader(
    navController: NavHostController,
    pagingPhotos: LazyPagingItems<Photo>,
    isCheckedIdx: State<Int>
) {
    TopAppBar(
        modifier = Modifier.padding(vertical = 8.dp),
        title = { Text("One Closet", fontWeight = FontWeight.ExtraBold) },
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.Filled.ArrowBack, contentDescription = "back")
            }
        },
        actions = {
            val color = if (isCheckedIdx.value != -1) PrimaryBlack else Gray
            Button(
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                onClick = {
                    val encodedUri = Uri.encode(pagingPhotos[isCheckedIdx.value]?.uri.toString())
                    Log.d(TAG, "GalleryHeader: $encodedUri")
                    navController.navigate("${ClothAnalysisNav.route}/${encodedUri}")
                }) {
                Text(text = "완료", color = color, fontWeight = FontWeight.ExtraBold)
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
    )
}
