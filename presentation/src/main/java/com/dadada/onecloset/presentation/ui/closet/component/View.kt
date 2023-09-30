package com.dadada.onecloset.presentation.ui.closet.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.navigation.NavHostController
import com.dadada.onecloset.domain.model.Closet
import com.dadada.onecloset.domain.model.clothes.ClothesInfo
import com.dadada.onecloset.presentation.R
import com.dadada.onecloset.presentation.ui.common.ClothInformRow
import com.dadada.onecloset.presentation.ui.common.ColorInformRow
import com.dadada.onecloset.presentation.ui.common.CustomTabRow
import com.dadada.onecloset.presentation.ui.common.DropDownMenu
import com.dadada.onecloset.presentation.ui.common.RoundedSquare
import com.dadada.onecloset.presentation.ui.common.RoundedSquareIconWithTitleItem
import com.dadada.onecloset.presentation.ui.common.RoundedSquareImageItem
import com.dadada.onecloset.presentation.ui.common.roundedSquareLargeModifier
import com.dadada.onecloset.presentation.ui.common.roundedSquareMediumModifier
import com.dadada.onecloset.presentation.ui.theme.BackGround
import com.dadada.onecloset.presentation.ui.theme.BackGroundGray
import com.dadada.onecloset.presentation.ui.theme.Paddings
import com.dadada.onecloset.presentation.ui.theme.PrimaryBlack
import com.dadada.onecloset.presentation.ui.theme.TextGray
import com.dadada.onecloset.presentation.ui.theme.Typography
import com.dadada.onecloset.presentation.ui.utils.hexStringToColor
import com.dadada.onecloset.presentation.ui.utils.iconHandler
import com.dadada.onecloset.presentation.viewmodel.closet.ClosetViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClothTabGridView(
    paddingValues: PaddingValues = PaddingValues(0.dp),
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    clothItems: List<ClothesInfo> = listOf(),
    icon: Int? = null,
    isSearch:Boolean = true,
    itemClickedStateList: List<Boolean> = mutableStateListOf(),
    onClick: (Int) -> Unit = {},
    onClickTab: (String) -> Unit = {},
    tabs: List<String> = listOf("전체", "상의", "하의", "외투", "한벌옷")
) {
    var selectedTabIndex by remember { mutableStateOf(0) }
    val tabWidths = remember {
        val tabWidthStateList = mutableStateListOf<Dp>()
        repeat(tabs.size) {
            tabWidthStateList.add(0.dp)
        }
        tabWidthStateList
    }

    val handleTabClick = { newIndex: Int ->
        selectedTabIndex = newIndex
        onClickTab(tabs[selectedTabIndex])
    }
    var searchQuery by remember { mutableStateOf("") }

    val filteredClothItems = if (searchQuery.isBlank()) {
        clothItems
    } else {
        clothItems.filter {
            it.airDressor.contains(searchQuery, ignoreCase = true) ||
                    it.color.contains(searchQuery, ignoreCase = true) ||
                    it.description.contains(searchQuery, ignoreCase = true) ||
                    it.dryer.contains(searchQuery, ignoreCase = true) ||
                    it.laundry.contains(searchQuery, ignoreCase = true) ||
                    it.material.contains(searchQuery, ignoreCase = true) ||
                    it.type.contains(searchQuery, ignoreCase = true) ||
                    it.upperType.contains(searchQuery, ignoreCase = true) ||
                    it.hashtagList.any { hashtag -> hashtag.contains(searchQuery, ignoreCase = true) } ||
                    it.tpoList.any { tpo -> tpo.contains(searchQuery, ignoreCase = true) } ||
                    it.weatherList.any { weather -> weather.contains(searchQuery, ignoreCase = true) }
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(26.dp))
            .background(Color.White)
            .padding(paddingValues)
    ) {
        CustomTabRow(
            modifier = Modifier,
            tabs = tabs,
            selectedTabIndex = selectedTabIndex,
            tabWidths = tabWidths,
            tabClick = handleTabClick
        )
        if(isSearch) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = Icons.Default.Search, contentDescription = "", tint = BackGroundGray)
                Spacer(modifier = Modifier.size(Paddings.medium))
                BasicTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    if (searchQuery.isEmpty()) {
                        Text(
                            text = "검색어를 입력하세요.",
                            style = Typography.bodyMedium.copy(color = TextGray),
                        )
                    }
                    it()
                }
            }
        }
        ClothGridView(
            navHostController = navHostController,
            clothItems = filteredClothItems,
            icon = icon,
            itemClickedStateList = itemClickedStateList,
            onClick = onClick
        )
    }
}

private const val TAG = "View"

@Composable
fun ClothGridView(
    navHostController: NavHostController,
    clothItems: List<ClothesInfo>,
    icon: Int? = null,
    itemClickedStateList: List<Boolean> = mutableStateListOf(),
    onClick: (Int) -> Unit = {}
) {
    var icon: Int? = icon

    LazyVerticalGrid(
        modifier = Modifier.padding(horizontal = 4.dp),
        columns = GridCells.Fixed(3),
    ) {
        items(clothItems.size) { it ->
            if (itemClickedStateList.isNotEmpty()) {
                icon =
                    if (itemClickedStateList[it]) R.drawable.ic_checked else R.drawable.ic_unchecked
            }
            RoundedSquareImageItem(
                modifier = roundedSquareMediumModifier,
                imageUri = clothItems[it].thumnailUrl.toUri(),
                icon = icon,
                onClick = { onClick(clothItems[it].clothesId) },
            )
        }
    }
}


@Composable
fun ClosetListView(
    closetList: List<Closet>,
    closetViewModel: ClosetViewModel,
    onClick: (Int) -> Unit,
) {
    Box(
        modifier = roundedSquareLargeModifier
    ) {
        LazyVerticalGrid(
            modifier = Modifier
                .align(Alignment.Center),
            columns = GridCells.Fixed(3),
        ) {
            items(closetList.size) {
                RoundedSquareIconWithTitleItem(
                    modifier = Modifier.padding(24.dp),
                    title = closetList[it].name,
                    icon = iconHandler(closetList[it].icon),
                    backGroundTint = hexStringToColor(closetList[it].colorCode),
                    onClick = {
                        closetViewModel.isBasicCloset = it == 0
                        onClick(closetList[it].closetId)
                    }
                )
            }
        }
    }
}

@Composable
fun ClothCourseView(titleList: List<String>, contentList: List<String>) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.weight(1f))
        Column() {
            titleList.forEachIndexed { index, title ->
                RoundedSquare(title = title, content = contentList[index])
                Spacer(modifier = Modifier.size(12.dp))
            }
        }
    }
}

@Composable
fun ClothInformView(cloth: ClothesInfo, onClick: () -> Unit = {}) {
    Column {
        Column(
            modifier = roundedSquareLargeModifier.padding(vertical = Paddings.large)
        ) {
            ClothInformRow("종류", cloth.type)
            ClothInformRow(title = "재질", content = cloth.material)
            ColorInformRow(
                title = "색상",
                content = hexStringToColor(cloth.colorCode),
                colorName = cloth.color
            )
        }
        Spacer(modifier = Modifier.size(Paddings.large))
        if (cloth.isEmptyAdditionalInfo()) {
            ClothNeedInputAdditionalInformView { onClick() }
        } else {
            ClothAdditionalInformView(cloth)
        }
    }

}

@Composable
fun ClothNeedInputAdditionalInformView(onClick: () -> Unit) {
    Row(
        modifier = roundedSquareLargeModifier
            .background(BackGroundGray)
            .padding(Paddings.large)
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_lamp),
                    contentDescription = "",
                    tint = TextGray
                )
                Text(
                    text = "Tip.",
                    style = Typography.bodyMedium.copy(
                        color = TextGray,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 14.sp
                    )
                )
            }
            Text(
                modifier = Modifier.padding(start = Paddings.medium),
                text = "추가 정보를 입력해\n더 스마트한 의류 관리를 경험해 보세요!",
                style = Typography.bodySmall.copy(
                    color = TextGray,
                    fontWeight = FontWeight.Bold
                )
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            imageVector = Icons.Default.KeyboardArrowRight,
            contentDescription = "",
            tint = TextGray
        )
    }
}

@Composable
fun ClothAdditionalInformView(clothesInfo: ClothesInfo) {
    Column(modifier = roundedSquareLargeModifier.padding(Paddings.large)) {
        Column(modifier = Modifier.padding(Paddings.large)) {
            ClothesAdditionalInfoRow(title = "설명")
            Text(text = clothesInfo.description)
            ClothesAdditionalInfoRow(title = "해쉬태그", clothesInfo.hashtagList)
            ClothesAdditionalInfoRow(title = "날씨", clothesInfo.weatherList)
            ClothesAdditionalInfoRow(title = "TPO", clothesInfo.tpoList)
        }
    }
}

@Composable
fun ClothesAdditionalInfoRow(title: String, contentList: List<String> = listOf()) {
    Column(modifier = Modifier.padding(vertical = Paddings.medium)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = title, fontWeight = FontWeight.ExtraBold)
        }

        if (contentList.isNotEmpty() && contentList[0] != "") {
            LazyVerticalGrid(modifier = Modifier.height(44.dp), columns = GridCells.Fixed(4)) {
                items(contentList.size) {
                    if (contentList[it] == "") {
                        return@items
                    }
                    SuggestionChip(
                        modifier = Modifier.padding(Paddings.small),
                        onClick = { /*TODO*/ },
                        label = { Text(text = contentList[it], color = Color.White) },
                        colors = SuggestionChipDefaults.suggestionChipColors(containerColor = PrimaryBlack)
                    )
                }
            }
        } else if (title != "설명") {
            Spacer(modifier = Modifier.size(Paddings.small))
            Text(text = "정보를 등록해주세요.", style = Typography.bodySmall.copy(color = TextGray))
        }
    }
}


@Composable
fun ClothInputAdditionalInformDialogView() {
    Column(modifier = roundedSquareLargeModifier.padding(Paddings.xlarge)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "계절")
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "TPO")
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "해쉬태그")
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "계절")
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClothHeader(
    navController: NavHostController,
    isEdit: Boolean = true,
    onClickEdit: () -> Unit,
    onClickDelete: () -> Unit
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
                        onClickDelete = onClickDelete
                    ) {
                        expandDropDown = !expandDropDown
                    }
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
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
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
    )
}


@Composable
fun PutClothAdditionalInfoView(
    title: String,
    contentList: List<String>,
    chipState: MutableState<MutableList<Boolean>>
) {
    Column {
        Text(
            text = title,
            style = Typography.titleMedium.copy(fontWeight = FontWeight.ExtraBold)
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