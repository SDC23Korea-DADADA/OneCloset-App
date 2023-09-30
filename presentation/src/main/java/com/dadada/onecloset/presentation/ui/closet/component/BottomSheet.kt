package com.dadada.onecloset.presentation.ui.closet.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dadada.onecloset.domain.model.Closet
import com.dadada.onecloset.domain.model.clothes.ClothesInfo
import com.dadada.onecloset.presentation.ui.theme.BackGround
import com.dadada.onecloset.presentation.ui.theme.Paddings
import com.dadada.onecloset.presentation.ui.theme.Typography
import com.dadada.onecloset.presentation.ui.utils.NetworkResultHandler
import com.dadada.onecloset.presentation.viewmodel.MainViewModel
import com.dadada.onecloset.presentation.viewmodel.closet.ClosetViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectClosetBottomSheet(
    modifier: Modifier = Modifier,
    mainViewModel: MainViewModel,
    sheetState: SheetState,
    onDismissRequest: () -> Unit,
    onClick: (Int) -> Unit,
    closetViewModel: ClosetViewModel = hiltViewModel()
) {
    val sheetState = rememberModalBottomSheetState()
    val closetListState by closetViewModel.closetListState.collectAsState()
    var closetList by remember { mutableStateOf(listOf<Closet>()) }

    LaunchedEffect(closetListState) {
        closetViewModel.getClosetList()
    }

    NetworkResultHandler(state = closetListState, mainViewModel = mainViewModel) {
        closetList = it
    }

    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = { onDismissRequest() },
        containerColor = BackGround
    ) {
        Column(modifier = modifier.padding(Paddings.xlarge)) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "옷장 선택",
                style = Typography.titleMedium.copy(fontWeight = FontWeight.ExtraBold),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.size(Paddings.large))

            ClosetListView(closetList = closetList, closetViewModel, onClick = onClick)
            Spacer(modifier = Modifier.size(56.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PutClothAdditionalInfoBottomSheet(
    cloth: ClothesInfo,
    closetViewModel: ClosetViewModel,
    onDismissRequest: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    val weatherContentList = listOf("봄", "여름", "가을", "겨울")
    val tpoContentList = listOf("데일리", "직장", "데이트", "경조사", "여행", "홈웨어", "운동", "기타")

    var weatherChipState =
        remember { mutableStateOf(MutableList(weatherContentList.size) { false }) }
    var tpoContentChipState =
        remember { mutableStateOf(MutableList(tpoContentList.size) { false }) }
    var descriptionState = remember { mutableStateOf("") }
    var hashtagState = remember { mutableStateOf("") }

    ModalBottomSheet(
        modifier = Modifier.wrapContentHeight(),
        sheetState = sheetState,
        onDismissRequest = {
            scope.launch { sheetState.hide() }
            onDismissRequest()
        },
        containerColor = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Paddings.xlarge)
        ) {
            PutClothAdditionalTextView(
                title = "설명",
                hint = "옷에 대한 설명을 적어주세요.",
                inputState = descriptionState
            )
            PutClothAdditionalTextView(
                title = "해쉬태그",
                hint = "쉼표로 해쉬태그를 구분할 수 있어요.",
                inputState = hashtagState
            )

            PutClothAdditionalInfoView(
                title = "날씨",
                contentList = weatherContentList,
                chipState = weatherChipState
            )
            PutClothAdditionalInfoView(
                title = "TPO",
                contentList = tpoContentList,
                chipState = tpoContentChipState
            )
            Button(modifier = Modifier.fillMaxWidth(), onClick = {
                closetViewModel.updateClothes(
                    clothesInfo = cloth.apply {
                        hashtagList = hashtagState.value.split(",")
                        weatherList = weatherContentList.filterIndexed {index, _ ->  weatherChipState.value[index]}
                        tpoList = tpoContentList.filterIndexed {index, _ ->  tpoContentChipState.value[index]}
                        description = descriptionState.value
                    }
                )
            }) {
                Text(text = "등록")
            }
            Spacer(modifier = Modifier.size(56.dp))
        }
    }
}