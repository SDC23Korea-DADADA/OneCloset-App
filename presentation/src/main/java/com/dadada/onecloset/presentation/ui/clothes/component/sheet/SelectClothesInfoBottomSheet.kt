package com.dadada.onecloset.presentation.ui.clothes.component.sheet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.dadada.onecloset.presentation.ui.components.CustomListItem
import com.dadada.onecloset.presentation.ui.theme.BackGround
import com.dadada.onecloset.presentation.ui.theme.BackGroundGray
import com.dadada.onecloset.presentation.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectClothesInfoBottomSheet(
    modifier: Modifier = Modifier,
    title: String,
    contentList: List<String>,
    sheetState: SheetState,
    onDismissRequest: () -> Unit,
    onClick: (String) -> Unit,
) {
    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = { onDismissRequest() },
        containerColor = BackGroundGray,
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = title,
                style = Typography.titleMedium,
                textAlign = TextAlign.Center,
            )

            Spacer(modifier = Modifier.size(12.dp))
            LazyColumn(
                modifier = Modifier
                    .clip(RoundedCornerShape(26.dp))
                    .background(Color.White),
            ) {
                items(contentList.size) {
                    CustomListItem(
                        content = contentList[it],
                        onClick = {
                            onClick(contentList[it])
                            onDismissRequest()
                        },
                    )
                }
            }
            Spacer(modifier = Modifier.size(56.dp))
        }
    }
}
