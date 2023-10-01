package com.dadada.onecloset.presentation.ui.clothes.component.sheet

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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.dadada.onecloset.domain.model.clothes.ClothesInfo
import com.dadada.onecloset.presentation.ui.closet.component.PutClothAdditionalInfoView
import com.dadada.onecloset.presentation.ui.closet.component.PutClothAdditionalTextView
import com.dadada.onecloset.presentation.ui.theme.Paddings
import com.dadada.onecloset.presentation.viewmodel.closet.ClosetViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClothesPutAdditionalInfoBottomSheet(
    modifier: Modifier = Modifier,
    sheetState: SheetState,
    cloth: ClothesInfo,
    closetViewModel: ClosetViewModel,
    onDismissRequest: () -> Unit,
) {
    val weatherContentList = listOf("봄", "여름", "가을", "겨울")
    val tpoContentList = listOf("데일리", "직장", "데이트", "경조사", "여행", "홈웨어", "운동", "기타")

    val weatherChipState =
        remember { mutableStateOf(MutableList(weatherContentList.size) { false }) }
    val tpoContentChipState =
        remember { mutableStateOf(MutableList(tpoContentList.size) { false }) }
    val descriptionState = remember { mutableStateOf("") }
    val hashtagState = remember { mutableStateOf("") }

    ModalBottomSheet(
        modifier = Modifier.wrapContentHeight(),
        sheetState = sheetState,
        onDismissRequest = { onDismissRequest() },
        containerColor = Color.White,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Paddings.xlarge),
        ) {
            PutClothAdditionalTextView(
                title = "설명",
                hint = "옷에 대한 설명을 적어주세요.",
                inputState = descriptionState,
            )
            PutClothAdditionalTextView(
                title = "해쉬태그",
                hint = "쉼표로 해쉬태그를 구분할 수 있어요.",
                inputState = hashtagState,
            )

            PutClothAdditionalInfoView(
                title = "날씨",
                contentList = weatherContentList,
                chipState = weatherChipState,
            )
            PutClothAdditionalInfoView(
                title = "TPO",
                contentList = tpoContentList,
                chipState = tpoContentChipState,
            )
            Button(modifier = Modifier.fillMaxWidth(), onClick = {
                closetViewModel.updateClothes(
                    clothesInfo = cloth.apply {
                        hashtagList = hashtagState.value.split(",")
                        weatherList =
                            weatherContentList.filterIndexed { index, _ -> weatherChipState.value[index] }
                        tpoList =
                            tpoContentList.filterIndexed { index, _ -> tpoContentChipState.value[index] }
                        description = descriptionState.value
                    },
                )
            }) {
                Text(text = "등록")
            }
            Spacer(modifier = Modifier.size(56.dp))
        }
    }
}
