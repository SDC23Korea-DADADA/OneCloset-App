package com.dadada.onecloset.presentation.ui.common

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.dadada.onecloset.domain.model.Closet
import com.dadada.onecloset.presentation.ui.theme.BackGround
import com.dadada.onecloset.presentation.ui.theme.Blue
import com.dadada.onecloset.presentation.ui.theme.Paddings
import com.dadada.onecloset.presentation.ui.theme.PrimaryBlack
import com.dadada.onecloset.presentation.ui.theme.Typography
import com.dadada.onecloset.presentation.ui.utils.ClothColor
import com.dadada.onecloset.presentation.ui.utils.ColorEnum
import com.dadada.onecloset.presentation.ui.utils.IconEnum
import com.dadada.onecloset.presentation.ui.utils.Material
import com.dadada.onecloset.presentation.ui.utils.PermissionRequester
import com.dadada.onecloset.presentation.ui.utils.Permissions
import com.dadada.onecloset.presentation.ui.utils.ShowToast
import com.dadada.onecloset.presentation.ui.utils.Type
import com.dadada.onecloset.presentation.ui.utils.colorToHexString
import com.dadada.onecloset.presentation.viewmodel.closet.ClosetViewModel
import kotlinx.coroutines.launch

private const val TAG = "BottomSheet"

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun BottomSheetAddCloset(closetViewModel: ClosetViewModel) {
    val iconResIds = IconEnum.values().map { it.resId }
    val colors = ColorEnum.values().map { it.color }

    var textValue by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }
    var selectedIconIdx = remember { mutableStateOf(0) }
    val selectedColor = remember { mutableStateOf(Blue) }
    val keyboardController = LocalSoftwareKeyboardController.current

    var showToast by remember {
        mutableStateOf(false)
    }
    if(showToast) {
        ShowToast(text = "이름을 등록해주세요!")
        showToast = !showToast
    }
    if (showDialog) {
        SelectClosetIconDialog(
            selectedIconIdx = selectedIconIdx,
            selectedColor = selectedColor,
            iconResIds = iconResIds,
            colors = colors,
            onDismissRequest = { showDialog = !showDialog },
        )
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "옷장 등록",
            style = Typography.titleMedium.copy(fontWeight = FontWeight.ExtraBold),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.size(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "대표 아이콘", style = Typography.titleSmall.copy(fontWeight = FontWeight.Bold))
            DropDownRow(component = {
                RoundedSquareIconItem(
                    icon = iconResIds[selectedIconIdx.value],
                    backGroundTint = selectedColor.value
                )

            }, reverse = showDialog, onClick = { showDialog = !showDialog })
        }

        Spacer(modifier = Modifier.size(12.dp))

        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "이름", style = Typography.titleSmall.copy(fontWeight = FontWeight.Bold))
            OutlinedTextField(
                value = textValue,
                onValueChange = { textValue = it },
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .onFocusChanged { focusState ->
                        if (focusState.isFocused) {
                            keyboardController?.show()
                            Log.d(TAG, "BottomSheetAddCloset: ")
                        }
                    }
            )
        }

        Spacer(modifier = Modifier.size(16.dp))

        Button(modifier = Modifier.fillMaxWidth(), onClick = {
            if(textValue == "") {
                showToast = !showToast
            } else {
                closetViewModel.putCloset(
                    Closet(
                        colorCode = colorToHexString(selectedColor.value),
                        icon = iconResIds[selectedIconIdx.value],
                        name = textValue
                    )
                )
            }
        }) {
            Text(text = "등록")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectTypeBottomSheet(show: MutableState<Boolean>, curType: String, onClick: (String) -> Unit) {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var chipIdx by remember { mutableStateOf(0) }
    val category = listOf("상의", "하의", "아우터", "한벌옷")
    var list by remember { mutableStateOf(Type.getTypesByCategory(category[chipIdx])) }
    LaunchedEffect(key1 = chipIdx) {
        list = Type.getTypesByCategory(category[chipIdx])
    }
    ModalBottomSheet(
        sheetState = sheetState, onDismissRequest = {
            scope.launch {
                sheetState.hide()
                show.value = !show.value
            }
        }, containerColor = BackGround
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "종류 선택",
                style = Typography.titleMedium.copy(fontWeight = FontWeight.ExtraBold),
                textAlign = TextAlign.Center
            )
            LazyRow(Modifier.padding(vertical = 16.dp)) {
                items(category.size) {
                    SuggestionChip(
                        modifier = Modifier.padding(4.dp),
                        onClick = { chipIdx = it },
                        label = {
                            Text(
                                text = category[it],
                                color = if (chipIdx == it) Color.White else Color.LightGray
                            )
                        },
                        colors = SuggestionChipDefaults.suggestionChipColors(containerColor = if (chipIdx == it) PrimaryBlack else Color.White),
                    )
                }
            }

            LazyColumn(
                modifier = Modifier
                    .clip(RoundedCornerShape(26.dp))
                    .background(Color.White)
            ) {
                items(list.size) {
                    CustomListItem(
                        content = list[it].name,
                        onClick = {
                            onClick(list[it].name)
                            show.value = !show.value
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.size(56.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectMaterialBottomSheet(
    show: MutableState<Boolean>,
    curMaterial: String,
    onClick: (String) -> Unit
) {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    val list = Material.getAllMaterial()
    ModalBottomSheet(
        sheetState = sheetState, onDismissRequest = {
            scope.launch {
                sheetState.hide()
                show.value = !show.value
            }
        }, containerColor = BackGround
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "재질 선택",
                style = Typography.titleMedium.copy(fontWeight = FontWeight.ExtraBold),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.size(12.dp))
            LazyColumn(
                modifier = Modifier
                    .clip(RoundedCornerShape(26.dp))
                    .background(Color.White)
            ) {
                items(list.size) {
                    CustomListItem(
                        content = list[it].name,
                        onClick = {
                            onClick(list[it].name)
                            show.value = !show.value
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.size(56.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectColorBottomSheet(
    show: MutableState<Boolean>,
    curColor: String,
    onClick: (ClothColor) -> Unit
) {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    val list = ClothColor.getAllColor()
    ModalBottomSheet(
        sheetState = sheetState, onDismissRequest = {
            scope.launch {
                sheetState.hide()
                show.value = !show.value
            }
        }, containerColor = BackGround
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "색상 선택",
                style = Typography.titleMedium.copy(fontWeight = FontWeight.ExtraBold),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.size(12.dp))
            LazyVerticalGrid(
                columns = GridCells.Adaptive(56.dp),
                modifier = Modifier
                    .clip(RoundedCornerShape(26.dp))
                    .background(Color.White)
            ) {
                items(list.size) {
                    // 다채색을 나타내는 아이템인 경우
                    if (it == list.size - 1) {
                        OtherColorItem(Modifier.clickable {
                            onClick(list[it])
                            show.value = !show.value
                        })
                    } else {
                        ColorItem(modifier = Modifier.clickable {
                            onClick(list[it])
                            show.value = !show.value
                        }, color = list[it].color, name = list[it].name)
                    }
                }
            }
            Spacer(modifier = Modifier.size(56.dp))
        }
    }
}

@Composable
fun OtherColorItem(modifier: Modifier = Modifier) {
    val gradientColors =
        listOf(Color.Red, Color.Blue, Color.Green) // 원하는 색상들로 수정
    Column(
        modifier = modifier.padding(Paddings.small),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .size(32.dp)
                .background(brush = Brush.linearGradient(colors = gradientColors))
                .border(1.dp, Color.Black, CircleShape)
        )
        Text(text = "다채색", style = Typography.bodySmall, color = Color.Black)
    }
}

@Composable
fun ColorItem(modifier: Modifier = Modifier, color: Color, name: String) {
    Column(
        modifier = modifier.padding(Paddings.small),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .size(32.dp)
                .background(color = color)
                .border(1.dp, Color.Black, CircleShape)
        )
        Text(text = name, style = Typography.bodySmall.copy(color = Color.Black))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectPhotoBottomSheet(
    onClickCamera: () -> Unit,
    onClickGallery: () -> Unit,
    onClick: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState()

    var cameraClickState by remember { mutableStateOf(false) }
    val cameraClick = { cameraClickState = !cameraClickState }
    if (cameraClickState) {
    }

    var galleryClickState by remember { mutableStateOf(false) }
    val galleryClick = { galleryClickState = !galleryClickState }
    if (galleryClickState) {
        PermissionRequester(
            permission = Permissions.readExternalStoragePermission,
            onDismissRequest = galleryClick,
            onPermissionGranted = onClickGallery
        ) {
        }
    }

    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = { onClick() },
        containerColor = BackGround
    ) {
        SelectPhotoView(onClickCamera = cameraClick, onClickGallery = onClickGallery)
        Spacer(modifier = Modifier.size(56.dp))
    }
}