package com.dadada.onecloset.presentation.ui.account.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.navigation.NavHostController
import com.dadada.onecloset.domain.model.fitting.FittingModelInfo
import com.dadada.onecloset.presentation.ui.NavigationItem
import com.dadada.onecloset.presentation.ui.account.model.SignInButton
import com.dadada.onecloset.presentation.ui.common.RoundedSquareImageItem
import com.dadada.onecloset.presentation.ui.common.roundedSquareLargeModifier
import com.dadada.onecloset.presentation.ui.theme.Gray
import com.dadada.onecloset.presentation.ui.theme.Paddings
import com.dadada.onecloset.presentation.ui.theme.Typography
import com.dadada.onecloset.presentation.viewmodel.fitting.FittingViewModel
import kotlinx.coroutines.launch

@Composable
fun AccountTitleView(title: String) {
    Text(
        modifier = Modifier.padding(
            start = Paddings.xlarge,
            top = Paddings.small,
            bottom = Paddings.small
        ),
        text = title,
        style = Typography.titleSmall.copy(color = Gray),
    )
}


@Composable
fun SignInButtonView(signInButtons: List<SignInButton>) {
    signInButtons.forEach {
        Image(
            modifier = Modifier
                .padding(horizontal = Paddings.xlarge)
                .clickable(onClick = it.action),
            painter = painterResource(id = it.id),
            contentDescription = it.description
        )
        Spacer(modifier = Modifier.size(12.dp))
    }
}

@Composable
fun AccountSingleLineSection(
    modifier: Modifier = Modifier,
    title: String,
    subTitle: String,
    content: String,
    onClick: () -> Unit = {}
) {
    Column(modifier = modifier.clickable { onClick() }) {
        AccountTitleView(title = title)
        Column(modifier = roundedSquareLargeModifier.padding(Paddings.xlarge)) {
            Text(text = subTitle, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.size(Paddings.small))
            Text(text = content, color = Color.DarkGray)
        }
    }
}

@Composable
fun AccountMultiLineSection(
    modifier: Modifier = Modifier,
    title: String,
    subTitleList: List<Int>,
    onClickList: List<() -> Unit>
) {
    Column(modifier = modifier) {
        AccountTitleView(title = title)

        Column(modifier = roundedSquareLargeModifier.padding(Paddings.xlarge)) {
            subTitleList.forEachIndexed { index, it ->
                Row(
                    modifier = Modifier
                        .padding(vertical = Paddings.medium)
                        .clickable(onClick = onClickList[index]),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = stringResource(id = it))
                    Spacer(modifier = Modifier.weight(1f))
                    if (it == AccountText.VERSION.id) {
                        Text(text = "1.0.0", color = Color.LightGray)
                    } else {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowRight,
                            contentDescription = "",
                            tint = Color.LightGray
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModelListView(
    navHostController: NavHostController,
    modelList: List<FittingModelInfo>,
    sheetState: SheetState,
    fittingViewModel: FittingViewModel
) {
    val scope = rememberCoroutineScope()
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "모델 선택",
            style = Typography.titleMedium.copy(fontWeight = FontWeight.ExtraBold),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.size(12.dp))
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier
                .clip(RoundedCornerShape(26.dp))
                .background(Color.White)
        ) {
            items(modelList.size) {
                if(modelList[it].regist) {
                    RoundedSquareImageItem(imageUri = modelList[it].modelImg.toUri(), icon = null) {
                        fittingViewModel.setFittingInfoModelId(modelList[it].modelId.toString())
                        scope.launch { sheetState.hide() }
                        navHostController.navigate(NavigationItem.FittingNav.route)
                    }
                } else {
                    RoundedSquareImageItem(isUploading = modelList[it].regist, imageUri = modelList[it].modelImg.toUri(), icon = null) {

                    }
                }

            }
        }
    }
}