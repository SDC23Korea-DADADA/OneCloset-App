package com.dadada.onecloset.presentation.ui.photo

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.dadada.onecloset.presentation.R
import com.dadada.onecloset.presentation.ui.NavigationItem
import com.dadada.onecloset.presentation.ui.common.circleShapeModifier
import com.dadada.onecloset.presentation.ui.photo.datasource.FileDataSource
import com.dadada.onecloset.presentation.ui.utils.Mode
import com.dadada.onecloset.presentation.ui.utils.NetworkResultHandler
import com.dadada.onecloset.presentation.viewmodel.PhotoViewModel
import com.dadada.onecloset.presentation.viewmodel.closet.ClosetViewModel
import com.dadada.onecloset.presentation.viewmodel.fitting.FittingViewModel
import com.ujizin.camposer.CameraPreview
import com.ujizin.camposer.state.CamSelector
import com.ujizin.camposer.state.CameraState
import com.ujizin.camposer.state.ImageCaptureResult
import com.ujizin.camposer.state.rememberCameraState
import kotlin.math.min

private const val TAG = "CameraScreen"

private val fileDataSource = FileDataSource()
@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun CameraScreen(
    navHostController: NavHostController,
    photoViewModel: PhotoViewModel = hiltViewModel(),
    closetViewModel: ClosetViewModel,
    fittingViewModel: FittingViewModel
) {
    val cameraState = rememberCameraState()
    val cameraSelector by remember { mutableStateOf(CamSelector.Back) }
    val context = LocalContext.current
    var sliderPosition by remember { mutableStateOf(1f) }

    val closetAnalysisState by closetViewModel.clothAnalysisState.collectAsState()
    NetworkResultHandler(state = closetAnalysisState) {
        closetViewModel.clothesInfo.image = it.image
        closetViewModel.clothesInfo.material = it.material
        closetViewModel.clothesInfo.colorCode = it.colorCode
        closetViewModel.clothesInfo.type = it.type
        closetViewModel.resetNetworkStates()
        navHostController.navigate(NavigationItem.ClothAnalysisNav.route)
    }

    CameraPreview(
        cameraState = cameraState,
        camSelector = cameraSelector,
        modifier = Modifier.padding(0.dp)
    ) {
        DrawGuidelinesAndFilter()
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 48.dp), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "의류를 영역에 맞추어 찍어주세요.")

            Spacer(modifier = Modifier.weight(1f))

            Box(modifier = circleShapeModifier
                .size(64.dp)
                .clickable {
                    cameraState.takePicture(
                        fileDataSource.imageContentValues
                    ) {
                        if(photoViewModel.curMode == Mode.clothes) {
                            closetViewModel.clothesInfo.image =
                                fileDataSource.getLastPictureUriPostQ(context).toString()
                            closetViewModel.putClothAnalysis(closetViewModel.clothesInfo.image)
                        } else {
                            fittingViewModel.putModel(fileDataSource.getLastPictureUriPostQ(context).toString())
                        }
                    }
                }) {
                Icon(
                    modifier = Modifier.align(Alignment.Center),
                    painter = painterResource(id = R.drawable.ic_camera),
                    contentDescription = ""
                )
            }

            Spacer(modifier = Modifier.size(44.dp))
        }

    }
}

@Composable
fun DrawGuidelinesAndFilter() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val canvasWidth = size.width
        val canvasHeight = size.height
        val squareSize = min(canvasWidth, canvasHeight) * 0.95f
        val offsetX = (canvasWidth - squareSize) / 2
        val offsetY = (canvasHeight - squareSize) / 2
        val cornerRadius = 20f  // 원하는 모서리의 반경 값을 설정합니다.

        // 전체 배경에 흰색 반투명 필터를 적용합니다.
        drawRect(color = Color.White.copy(alpha = 0.5f))

        val rectPath = Path().apply {
            addRoundRect(
                RoundRect(
                    left = offsetX,
                    top = offsetY,
                    right = offsetX + squareSize,
                    bottom = offsetY + squareSize,
                    cornerRadius,
                    cornerRadius
                )
            )
        }

        // 정사각형 모양의 홀을 뚫습니다.
        drawPath(path = rectPath, color = Color.Transparent, blendMode = BlendMode.Clear)
    }

}

