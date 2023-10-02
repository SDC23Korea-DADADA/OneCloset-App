package com.dadada.onecloset.presentation.ui.closet.component.sheet

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dadada.onecloset.domain.model.Closet
import com.dadada.onecloset.presentation.ui.closet.component.view.ClosetListView
import com.dadada.onecloset.presentation.ui.theme.BackGround
import com.dadada.onecloset.presentation.ui.theme.Paddings
import com.dadada.onecloset.presentation.ui.theme.Typography
import com.dadada.onecloset.presentation.ui.utils.NetworkResultHandler
import com.dadada.onecloset.presentation.viewmodel.MainViewModel
import com.dadada.onecloset.presentation.viewmodel.closet.ClosetViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClosetListBottomSheet(
    modifier: Modifier = Modifier,
    mainViewModel: MainViewModel,
    sheetState: SheetState,
    onDismissRequest: () -> Unit,
    onClick: (Int) -> Unit,
    closetViewModel: ClosetViewModel = hiltViewModel(),
) {
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
        containerColor = BackGround,
    ) {
        Column(modifier = modifier.padding(Paddings.xlarge)) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "옷장 선택",
                style = Typography.titleMedium.copy(fontWeight = FontWeight.ExtraBold),
                textAlign = TextAlign.Center,
            )

            Spacer(modifier = Modifier.size(Paddings.large))
            ClosetListView(closetList = closetList, closetViewModel, onClick = onClick)
            Spacer(modifier = Modifier.size(56.dp))
        }
    }
}
