package com.dadada.onecloset.presentation.ui.coordi.component.view

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.navigation.NavHostController
import com.dadada.onecloset.domain.model.codi.Clothes
import com.dadada.onecloset.presentation.ui.NavigationItem
import com.dadada.onecloset.presentation.ui.components.RoundedSquareImageItem
import com.dadada.onecloset.presentation.ui.theme.Paddings
import com.dadada.onecloset.presentation.ui.theme.roundedSquareMediumModifier

@Composable
fun CoordiResultView(
    imagePath: String,
    clothesList: List<Clothes>,
    navController: NavHostController,
) {
    RoundedSquareImageItem(imageUri = imagePath.toUri(), icon = null) {
        val encodedPath = Uri.encode(imagePath)
        navController.navigate("${NavigationItem.PhotoNav.route}/$encodedPath")
    }

    Spacer(modifier = Modifier.size(Paddings.large))
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(26.dp))
            .background(Color.White)
            .padding(Paddings.large),
    ) {
        LazyVerticalGrid(
            modifier = Modifier.padding(horizontal = 4.dp),
            columns = GridCells.Fixed(3),
        ) {
            items(clothesList.size) {
                RoundedSquareImageItem(
                    modifier = roundedSquareMediumModifier,
                    imageUri = clothesList[it].thumbnailImg.toUri(),
                    icon = null,
                ) {
                    navController.navigate("${NavigationItem.ClothNav.route}/${clothesList[it].clothesId}")
                }
            }
        }
    }
}
