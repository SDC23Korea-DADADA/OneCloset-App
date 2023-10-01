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
import androidx.compose.runtime.DisposableEffect
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
import com.dadada.onecloset.presentation.ui.components.TwoButtonDialog
import com.dadada.onecloset.presentation.ui.components.circleShapeModifier
import com.dadada.onecloset.presentation.ui.photo.datasource.FileDataSource
import com.dadada.onecloset.presentation.ui.utils.LoadingType
import com.dadada.onecloset.presentation.ui.utils.Mode
import com.dadada.onecloset.presentation.ui.utils.NetworkResultHandler
import com.dadada.onecloset.presentation.ui.utils.ShowToast
import com.dadada.onecloset.presentation.viewmodel.MainViewModel
import com.dadada.onecloset.presentation.viewmodel.PhotoViewModel
import com.dadada.onecloset.presentation.viewmodel.closet.ClosetViewModel
import com.dadada.onecloset.presentation.viewmodel.codi.CodiViewModel
import com.dadada.onecloset.presentation.viewmodel.fitting.FittingViewModel
import com.ujizin.camposer.CameraPreview
import com.ujizin.camposer.state.CamSelector
import com.ujizin.camposer.state.rememberCameraState
import kotlin.math.min

private const val TAG = "CameraScreen"

private val fileDataSource = FileDataSource()
@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun CameraScreen(
    navHostController: NavHostController,
    mainViewModel: MainViewModel,
    photoViewModel: PhotoViewModel = hiltViewModel(),
    closetViewModel: ClosetViewModel,
    fittingViewModel: FittingViewModel,
    codiViewModel: CodiViewModel
) {
    DisposableEffect(Unit) {
        onDispose { closetViewModel.resetNetworkStates() }
    }
    val cameraState = rememberCameraState()
    val cameraSelector by remember { mutableStateOf(CamSelector.Back) }
    val context = LocalContext.current
    var sliderPosition by remember { mutableStateOf(1f) }

    val validationState by closetViewModel.clothesValidationState.collectAsState()
    val closetAnalysisState by closetViewModel.clothAnalysisState.collectAsState()
    NetworkResultHandler(state = closetAnalysisState, loadingType = LoadingType.ANALYSIS,mainViewModel = mainViewModel) {
        closetViewModel.clothesInfo.image = it.image
        closetViewModel.clothesInfo.material = it.material
        closetViewModel.clothesInfo.colorCode = it.colorCode
        closetViewModel.clothesInfo.type = it.type
        closetViewModel.resetNetworkStates()
        navHostController.navigate(NavigationItem.ClothAnalysisNav.route)
    }

    var showDialog by remember { mutableStateOf(false) }
    if (showDialog) {
        TwoButtonDialog(
            onDismissRequest = { showDialog = !showDialog },
            onConfirmation = {
                closetViewModel.putClothAnalysis(closetViewModel.clothesInfo.image)
                showDialog = !showDialog
            },
            dialogTitle = "알림",
            dialogText = "의류가 아닙니다!\n그래도 계속 할까요?",
        )
    }

    NetworkResultHandler(state = validationState, loadingType = LoadingType.VALIDATION,mainViewModel = mainViewModel) {
        if (it) {
            closetViewModel.putClothAnalysis(closetViewModel.clothesInfo.image)
        } else {
            showDialog = !showDialog
        }
    }

    var showToast by remember { mutableStateOf(false) }
    if(showToast) { ShowToast(text = "모델 등록에 약 1분이 소요돼요!") }

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
                        val curImagePath = fileDataSource
                            .getLastPictureUriPostQ(context)
                            .toString()
                        when (photoViewModel.curMode) {
                            Mode.clothes -> {
                                closetViewModel.checkClothes(curImagePath)
                                closetViewModel.clothesInfo.image = curImagePath
                            }

                            Mode.codi -> {
                                codiViewModel.codiRegisterInfo.imagePath = curImagePath
                                closetViewModel.resetNetworkStates()
                                navHostController.navigate(NavigationItem.CoordinationRegisterNav.route)
                            }

                            else -> {
                                fittingViewModel.putModel(curImagePath)
                                showToast = true
                                closetViewModel.resetNetworkStates()
                                navHostController.popBackStack()
                            }
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

