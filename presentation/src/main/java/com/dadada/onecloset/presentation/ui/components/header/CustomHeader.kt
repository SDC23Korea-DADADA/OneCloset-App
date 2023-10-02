package com.dadada.onecloset.presentation.ui.components.header

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
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
import com.dadada.onecloset.presentation.ui.theme.roundedSquareMediumModifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomHeader(
    navController: NavHostController,
    isMore: Boolean = false,
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
            if(isMore) {
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
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent),
    )
}
