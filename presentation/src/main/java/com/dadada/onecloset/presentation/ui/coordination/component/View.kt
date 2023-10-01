package com.dadada.onecloset.presentation.ui.coordination.component

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.navigation.NavHostController
import com.dadada.onecloset.domain.model.codi.Clothes
import com.dadada.onecloset.presentation.R
import com.dadada.onecloset.presentation.ui.NavigationItem
import com.dadada.onecloset.presentation.ui.components.RoundedSquareIconWithTitleItem
import com.dadada.onecloset.presentation.ui.components.RoundedSquareImageItem
import com.dadada.onecloset.presentation.ui.components.roundedSquareLargeModifier
import com.dadada.onecloset.presentation.ui.components.roundedSquareMediumModifier
import com.dadada.onecloset.presentation.ui.theme.BackGroundGray
import com.dadada.onecloset.presentation.ui.theme.Paddings
import com.dadada.onecloset.presentation.ui.theme.PointGreen
import com.dadada.onecloset.presentation.ui.theme.PrimaryBlue
import com.dadada.onecloset.presentation.ui.theme.TextGray
import com.dadada.onecloset.presentation.ui.theme.Typography
import com.dadada.onecloset.presentation.viewmodel.codi.CodiViewModel


@Composable
fun SelectCodiView(onClickRecord: () -> Unit, onClickPlan: () -> Unit) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp, top = 8.dp, start = 16.dp, end = 16.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        RoundedSquareIconWithTitleItem(
            modifier = Modifier.size(80.dp),
            title = "기록",
            icon = R.drawable.ic_daily,
            backGroundTint = PrimaryBlue,
            onClick = onClickRecord
        )

        RoundedSquareIconWithTitleItem(
            modifier = Modifier.size(80.dp),
            title = "계획",
            icon = R.drawable.ic_lamp,
            backGroundTint = PointGreen,
            onClick = onClickPlan
        )
    }
}

@Composable
fun CoordinationListView(navHostController: NavHostController, imageList: List<String>) {
    Box(
        modifier = roundedSquareLargeModifier.aspectRatio(1f)
    ) {
        LazyVerticalGrid(
            modifier = Modifier
                .align(Alignment.Center),
            columns = GridCells.Fixed(3),
        ) {
            items(imageList.size) {
                RoundedSquareImageItem(
                    modifier = roundedSquareMediumModifier,
                    imageUri = imageList[it].toUri(),
                    icon = null,
                    onClick = { },
                )
            }
        }
    }
}

@Composable
fun CodiResultView(
    imagePath: String,
    clothesList: List<Clothes>,
    navController: NavHostController,
    codiViewModel: CodiViewModel
) {
    RoundedSquareImageItem(imageUri = imagePath.toUri(), icon = null) {
        val encodedPath = Uri.encode(imagePath)
        navController.navigate("${NavigationItem.PhotoNav.route}/${encodedPath}")
    }

    Spacer(modifier = Modifier.size(Paddings.large))
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(26.dp))
            .background(Color.White)
            .padding(Paddings.large)
    ) {
        LazyVerticalGrid(
            modifier = Modifier.padding(horizontal = 4.dp),
            columns = GridCells.Fixed(3)
        ) {
            items(clothesList.size) {
                RoundedSquareImageItem(
                    modifier = roundedSquareMediumModifier,
                    imageUri = clothesList[it].thumbnailImg.toUri(),
                    icon = null
                ) {
                    navController.navigate("${NavigationItem.ClothNav.route}/${clothesList[it].clothesId}")
                }
            }
        }
    }
}

@Composable
fun EmptyView(title: String) {
    Column {
        Spacer(modifier = Modifier.weight(1f))
        Column(
            modifier = roundedSquareLargeModifier
                .background(BackGroundGray)
                .padding(Paddings.xlarge)
        ) {
            Text(
                text = title,
                style = Typography.bodyMedium.copy(
                    color = TextGray,
                    fontWeight = FontWeight.ExtraBold,
                )
            )
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}