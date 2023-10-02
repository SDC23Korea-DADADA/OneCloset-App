package com.dadada.onecloset.presentation.ui.closet.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.dadada.onecloset.presentation.ui.components.DropDownMenu
import com.dadada.onecloset.presentation.ui.theme.Paddings
import com.dadada.onecloset.presentation.ui.theme.PrimaryBlack
import com.dadada.onecloset.presentation.ui.theme.TextGray
import com.dadada.onecloset.presentation.ui.theme.Typography
import com.dadada.onecloset.presentation.ui.theme.roundedSquareMediumModifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClothHeader(
    navController: NavHostController,
    isEdit: Boolean = true,
    onClickEdit: () -> Unit,
    onClickDelete: () -> Unit,
) {
    var expandDropDown by remember {
        mutableStateOf(false)
    }

    TopAppBar(
        modifier = Modifier.padding(vertical = 8.dp),
        title = { Text("One Closet", fontWeight = FontWeight.ExtraBold) },
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.Filled.ArrowBack, contentDescription = "back")
            }
        },
        actions = {
            Box {
                IconButton(onClick = {
                    expandDropDown = !expandDropDown
                }) {
                    Icon(Icons.Filled.MoreVert, contentDescription = "more options")
                }
                if (expandDropDown) {
                    DropDownMenu(
                        modifier = roundedSquareMediumModifier,
                        isEdit = isEdit,
                        expanded = expandDropDown,
                        onClickEdit = onClickEdit,
                        onClickDelete = onClickDelete,
                    ) {
                        expandDropDown = !expandDropDown
                    }
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent),
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BasicHeader(
    navController: NavHostController,
) {
    TopAppBar(
        modifier = Modifier.padding(vertical = 8.dp),
        title = { Text("One Closet", fontWeight = FontWeight.ExtraBold) },
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.Filled.ArrowBack, contentDescription = "back")
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent),
    )
}

@Composable
fun PutClothAdditionalInfoView(
    title: String,
    contentList: List<String>,
    chipState: MutableState<MutableList<Boolean>>,
) {
    Column {
        Text(
            text = title,
            style = Typography.titleMedium.copy(fontWeight = FontWeight.ExtraBold),
        )
        LazyVerticalGrid(columns = GridCells.Fixed(5)) {
            items(contentList.size) {
                SuggestionChip(
                    modifier = Modifier.padding(Paddings.xsmall),
                    onClick = {
                        chipState.value = chipState.value.toMutableList().also { state ->
                            state[it] = !state[it]
                        }
                    },
                    label = {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = contentList[it],
                            color = if (chipState.value[it]) Color.White else Color.LightGray,
                        )
                    },
                    colors = SuggestionChipDefaults.suggestionChipColors(containerColor = if (chipState.value[it]) PrimaryBlack else Color.White),
                )
            }
        }
    }
}

@Composable
fun PutClothAdditionalTextView(title: String, hint: String, inputState: MutableState<String>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = title,
            style = Typography.titleMedium.copy(fontWeight = FontWeight.ExtraBold),
        )
        Spacer(modifier = Modifier.size(Paddings.medium))
        BasicTextField(
            value = inputState.value,
            onValueChange = { inputState.value = it },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),

        ) {
            if (inputState.value.isEmpty()) {
                Text(
                    text = hint,
                    style = Typography.bodyMedium.copy(color = TextGray),
                )
            }
            it()
        }
    }
}
