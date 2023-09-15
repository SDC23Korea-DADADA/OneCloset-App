package com.dadada.onecloset.presentation.ui.photo

import android.content.Context
import androidx.compose.foundation.Canvas
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
import com.dadada.onecloset.presentation.R
import com.dadada.onecloset.presentation.ui.common.circleShapeModifier
import com.dadada.onecloset.presentation.ui.common.roundedSquareLargeModifier
import com.ujizin.camposer.CameraPreview
import com.ujizin.camposer.state.CamSelector
import com.ujizin.camposer.state.rememberCameraState
import java.io.File
import kotlin.math.min

@Composable
fun CameraScreen() {
    val cameraState = rememberCameraState()
    val cameraSelector by remember { mutableStateOf(CamSelector.Back) }
    val context = LocalContext.current
    var sliderPosition by remember { mutableStateOf(1f) }

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

            Box(modifier = circleShapeModifier.size(64.dp)) {
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

private fun Context.createNewFile() = File(
    filesDir, "${System.currentTimeMillis()}.jpg"
).apply { createNewFile() }