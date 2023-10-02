package com.dadada.onecloset.presentation.ui.clothes.component.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dadada.onecloset.presentation.ui.clothes.component.row.ClothesCourseInfoRow

@Composable
fun ClothesCourseView(titleList: List<String>, contentList: List<String>) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Column {
            titleList.forEachIndexed { index, title ->
                ClothesCourseInfoRow(title = title, content = contentList[index])
                Spacer(modifier = Modifier.size(12.dp))
            }
        }
    }
}
