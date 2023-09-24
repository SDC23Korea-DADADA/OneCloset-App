package com.dadada.onecloset.presentation.ui.photo

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.dadada.onecloset.domain.model.NetworkResult
import com.dadada.onecloset.domain.model.Photo
import com.dadada.onecloset.presentation.ui.NavigationItem
import com.dadada.onecloset.presentation.ui.common.GalleryPhotoItem
import com.dadada.onecloset.presentation.ui.common.PhotoItem
import com.dadada.onecloset.presentation.ui.theme.Gray
import com.dadada.onecloset.presentation.ui.theme.PrimaryBlack
import com.dadada.onecloset.presentation.ui.utils.Mode
import com.dadada.onecloset.presentation.ui.utils.NetworkResultHandler
import com.dadada.onecloset.presentation.ui.utils.PermissionRequester
import com.dadada.onecloset.presentation.ui.utils.Permissions
import com.dadada.onecloset.presentation.ui.utils.ShowToast
import com.dadada.onecloset.presentation.viewmodel.PhotoViewModel
import com.dadada.onecloset.presentation.viewmodel.closet.ClosetViewModel
import com.dadada.onecloset.presentation.viewmodel.codi.CodiViewModel
import com.dadada.onecloset.presentation.viewmodel.fitting.FittingViewModel

private const val TAG = "GalleryScreen"

@Composable
fun GalleryScreen(
    navController: NavHostController,
    photoViewModel: PhotoViewModel = hiltViewModel(),
    closetViewModel: ClosetViewModel,
    fittingViewModel: FittingViewModel,
    codiViewModel: CodiViewModel,
) {
    val closetAnalysisState by closetViewModel.clothAnalysisState.collectAsState()
    NetworkResultHandler(state = closetAnalysisState) {
        closetViewModel.clothesInfo.image = it.image
        closetViewModel.clothesInfo.material = it.material
        closetViewModel.clothesInfo.colorCode = it.colorCode
        closetViewModel.clothesInfo.type = it.type
        closetViewModel.resetNetworkStates()
        navController.navigate(NavigationItem.ClothAnalysisNav.route)
    }


    val pagingPhotos = photoViewModel.photoList.collectAsLazyPagingItems()
    val isCheckedIdx = photoViewModel.isCheckedIdx.collectAsState()
    var onClick by remember { mutableStateOf(false) }

    if(onClick) {
        PermissionRequester(
            permission = Permissions.cameraPermission,
            onDismissRequest = { onClick = !onClick },
            onPermissionGranted = { navController.navigate(NavigationItem.CameraNav.route) }) {
            onClick = !onClick
        }
    }

    LaunchedEffect(Unit) {
        photoViewModel.getPagingPhotos()
    }
    val codiPutState by codiViewModel.codiPutState.collectAsState()
    NetworkResultHandler(state = codiPutState) {
        codiViewModel.resetNetworkStates()
        navController.popBackStack()
    }

    Scaffold(
        topBar = {
            GalleryHeader(
                navController = navController,
                pagingPhotos = pagingPhotos,
                isCheckedIdx = isCheckedIdx,
                closetViewModel = closetViewModel,
                photoViewModel = photoViewModel,
                fittingViewModel = fittingViewModel,
                codiViewModel = codiViewModel
            )
        }
    ) {
        LazyVerticalGrid(
            modifier = Modifier.padding(it),
            columns = GridCells.Fixed(4),
        ) {
            items(pagingPhotos.itemCount + 1) { index ->
                // 인덱스 0에는 카메라 아이콘을 표시
                if (index == 0) {
                    PhotoItem { onClick = !onClick }
                } else {
                    // 0이 아닌 다른 인덱스에는 GalleryPhotoItem 표시
                    val actualIndex = index - 1
                    val isCurrentItemChecked = isCheckedIdx.value == actualIndex
                    pagingPhotos[actualIndex]?.let { galleryImage ->
                        GalleryPhotoItem(url = galleryImage.uri, actualIndex, isCurrentItemChecked) {
                            photoViewModel.setCheckedIndex(actualIndex)
                        }
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
    isCheckedIdx: State<Int>,
    fittingViewModel: FittingViewModel,
    closetViewModel: ClosetViewModel,
    photoViewModel: PhotoViewModel,
    codiViewModel: CodiViewModel
) {
    var showToast by remember { mutableStateOf(false) }
    if(showToast) { ShowToast(text = "모델 등록에 약 2분이 소요돼요!") }
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
                    if(isCheckedIdx.value == -1) {
                        return@Button
                    }
                    when (photoViewModel.curMode) {
                        Mode.clothes -> {
                            closetViewModel.clothesInfo.image = pagingPhotos[isCheckedIdx.value]?.uri.toString()
                            closetViewModel.putClothAnalysis(closetViewModel.clothesInfo.image)
                        }
                        Mode.codi -> {
                            codiViewModel.putCodi(pagingPhotos[isCheckedIdx.value]?.uri.toString())
                        }
                        else -> {
                            fittingViewModel.putModel(pagingPhotos[isCheckedIdx.value]?.uri.toString())
                            showToast = true
                            navController.popBackStack()
                        }
                    }
                }) {
                Text(text = "완료", color = color, fontWeight = FontWeight.ExtraBold)
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
    )

}
