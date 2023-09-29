package com.dadada.onecloset.presentation.ui.coordination.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.dadada.onecloset.domain.model.codi.Codi
import com.dadada.onecloset.domain.model.codi.CodiList
import com.dadada.onecloset.domain.model.codi.Fitting
import com.dadada.onecloset.presentation.model.HorizontalCalendarConfig
import com.dadada.onecloset.presentation.ui.NavigationItem
import com.dadada.onecloset.presentation.ui.theme.BackGround
import com.dadada.onecloset.presentation.ui.theme.BackGroundGray
import com.dadada.onecloset.presentation.ui.theme.Paddings
import com.dadada.onecloset.presentation.ui.theme.PrimaryBlack
import com.dadada.onecloset.presentation.ui.theme.Typography
import com.dadada.onecloset.presentation.ui.utils.Mode
import com.dadada.onecloset.presentation.ui.utils.NetworkResultHandler
import com.dadada.onecloset.presentation.ui.utils.dateFormat
import com.dadada.onecloset.presentation.viewmodel.PhotoViewModel
import com.dadada.onecloset.presentation.viewmodel.codi.CodiViewModel
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.util.Locale

private const val TAG = "Calendar"

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun HorizontalCalendar(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    currentDate: LocalDate = LocalDate.now(),
    config: HorizontalCalendarConfig = HorizontalCalendarConfig(),
    codiViewModel: CodiViewModel,
    photoViewModel: PhotoViewModel,
    onSelectedDate: (LocalDate) -> Unit,
) {
    val codiListState by codiViewModel.codiListByMonth.collectAsState()
    var codiList by remember { mutableStateOf(CodiList(listOf(), listOf())) }
    val initialPage: Int =
        (currentDate.year - config.yearRange.first) * 12 + currentDate.monthValue - 1
    var currentSelectedDate by remember { mutableStateOf(currentDate) }
    var currentMonth: YearMonth by remember { mutableStateOf(YearMonth.now()) }
    var currentPage by remember { mutableStateOf(initialPage) }
    val pagerState = rememberPagerState(
        initialPage = initialPage,
        initialPageOffsetFraction = 0f
    ) {
        (config.yearRange.last - config.yearRange.first) * 12
    }

    NetworkResultHandler(state = codiListState) {
        codiList = it
    }

    LaunchedEffect(pagerState.currentPage) {
        val addMonth = (pagerState.currentPage - currentPage).toLong()
        currentMonth = currentMonth.plusMonths(addMonth)
        currentPage = pagerState.currentPage
        codiViewModel.getCodiListByMonth(currentMonth.toString())
    }

    LaunchedEffect(currentSelectedDate) {
        onSelectedDate(currentSelectedDate)
    }

    val scope = rememberCoroutineScope()
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        val headerText = currentMonth.dateFormat("yyyy년 M월")
        CalendarHeader(
            modifier = Modifier.padding(20.dp),
            text = headerText,
            onClickLeft = {
                scope.launch {
                    val targetPage = maxOf(pagerState.currentPage - 1, 0)
                    pagerState.animateScrollToPage(targetPage)
                }
            },
            onClickRight = {
                scope.launch {
                    val targetPage = minOf(pagerState.currentPage + 1, pagerState.pageCount - 1)
                    pagerState.animateScrollToPage(targetPage)
                }
            }
        )

        HorizontalPager(
            state = pagerState
        ) { page ->
            val date = LocalDate.of(
                config.yearRange.first + page / 12, page % 12 + 1, 1
            )
            if (page in pagerState.currentPage - 1..pagerState.currentPage + 1) { // 페이징 성능 개선을 위한 조건문
                CalendarMonthItem(
                    modifier = Modifier.fillMaxWidth(),
                    currentDate = date,
                    currentMonth = currentMonth.toString(),
                    selectedDate = currentSelectedDate,
                    onSelectedDate = { date ->
                        currentSelectedDate = date
                        codiViewModel.codiRegisterInfo.date = date.toString()
                    },
                    navController = navController,
                    codiList = codiList,
                    photoViewModel = photoViewModel,
                    codiViewModel = codiViewModel
                )
            }
        }
    }
}

@Composable
fun CalendarHeader(
    modifier: Modifier = Modifier,
    text: String,
    onClickLeft: () -> Unit,
    onClickRight: () -> Unit
) {
    Row(modifier = modifier) {
        Icon(modifier = Modifier.clickable { onClickLeft() }, imageVector = Icons.Default.KeyboardArrowLeft, contentDescription = "")
        Spacer(modifier = Modifier.size(Paddings.extra))
        Text(
            text = text, style = Typography.titleLarge.copy(fontWeight = FontWeight.ExtraBold)
        )
        Spacer(modifier = Modifier.size(Paddings.extra))
        Icon(modifier = Modifier.clickable { onClickRight() }, imageVector = Icons.Default.KeyboardArrowRight, contentDescription = "")
    }
}

@Composable
fun CalendarMonthItem(
    modifier: Modifier = Modifier,
    currentMonth: String,
    currentDate: LocalDate,
    selectedDate: LocalDate,
    onSelectedDate: (LocalDate) -> Unit,
    navController: NavHostController,
    codiList: CodiList,
    photoViewModel: PhotoViewModel,
    codiViewModel: CodiViewModel
) {
    val lastDay by remember { mutableStateOf(currentDate.lengthOfMonth()) }
    val firstDayOfWeek by remember { mutableStateOf(currentDate.dayOfWeek.value) }
    val days by remember { mutableStateOf(IntRange(1, lastDay).toList()) }

    Column(modifier = modifier) {
        DayOfWeek()
        LazyVerticalGrid(
            modifier = Modifier.fillMaxHeight(), columns = GridCells.Fixed(7)
        ) {
            if (firstDayOfWeek < 7) {
                for (i in 1 until firstDayOfWeek + 1) { // 처음 날짜가 시작하는 요일 전까지 빈 박스 생성
                    item {
                        Box(
                            modifier = Modifier
                                .size(30.dp)
                                .padding(top = 10.dp)
                        )
                    }
                }
            }
            items(days) { day ->
                var curDay = day.toString()
                if(curDay.length < 2) {
                    curDay = "0$curDay"
                }
                val curFittingItem = codiList.fittingList.find { it.wearingAtDay == "$currentMonth-$curDay" }
                val curDailyItem = codiList.codiList.find { it.wearingAtDay == "$currentMonth-$curDay" }

                var imageUrl = curFittingItem?.fittingThumbnailImg
                if (curDailyItem != null) {
                    imageUrl = curDailyItem.thumbnailImg
                }

                val date = currentDate.withDayOfMonth(day)
                val isSelected = remember(selectedDate) {
                    selectedDate.compareTo(date) == 0
                }
                CalendarDay(
                    modifier = Modifier.padding(top = 10.dp),
                    date = date,
                    isToday = date == LocalDate.now(),
                    isSelected = isSelected,
                    onSelectedDate = onSelectedDate,
                    navController = navController,
                    imageUrl = imageUrl,
                    codiViewModel = codiViewModel,
                    photoViewModel = photoViewModel,
                    curFittingItem = curFittingItem,
                    curDailyItem = curDailyItem
                )
            }
        }
    }
}


@Composable
fun CalendarDay(
    modifier: Modifier = Modifier,
    date: LocalDate,
    isToday: Boolean,
    isSelected: Boolean,
    onSelectedDate: (LocalDate) -> Unit,
    navController: NavHostController,
    codiViewModel: CodiViewModel,
    photoViewModel: PhotoViewModel,
    imageUrl: String?,
    curFittingItem: Fitting?,
    curDailyItem: Codi?
) {
    val current = LocalDate.now()
    val hasEvent = false
    val modifierClickable = if (imageUrl == null && date <= current) modifier.clickable {
        onSelectedDate(date)
        photoViewModel.curMode = Mode.codi
        var curDate = date.toString()
        if(curDate.length < 2) {
            curDate = "0$curDate"
        }
        codiViewModel.codiRegisterInfo.date = curDate
        navController.navigate(NavigationItem.GalleryNav.route)
    } else if (imageUrl == null && date > current) {
        modifier
    } else {
        modifier.clickable {
            onSelectedDate(date)
            if (curFittingItem != null) {
                codiViewModel.curFittingItem = curFittingItem
            }
            if (curDailyItem != null) {
                codiViewModel.curDailyCodiItem = curDailyItem
            }
            navController.navigate(NavigationItem.CoordinationResultNav.route)
        }
    }
    Box(
        modifier = modifierClickable
            .fillMaxWidth()
            .aspectRatio(0.7f)
            .clip(shape = RoundedCornerShape(10.dp)),
        contentAlignment = Alignment.Center
    ) {
        if(imageUrl != null) {
            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .clip(CircleShape)
                    .border(1.dp, BackGroundGray, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(model = imageUrl, contentDescription = "", contentScale = ContentScale.Crop)
            }
        }
        Text(
            textAlign = TextAlign.Center,
            text = date.dayOfMonth.toString(),
            fontWeight = FontWeight.Bold,
            color = if (imageUrl == null) Color.Black else Color.White
        )

    }
}

@Composable
fun DayOfWeek(
    modifier: Modifier = Modifier
) {
    Row(modifier) {
        val reorderedDays =
            listOf(DayOfWeek.SUNDAY) + DayOfWeek.values().filter { it != DayOfWeek.SUNDAY }
        reorderedDays.forEach { dayOfWeek ->
            val color =
                if (dayOfWeek == DayOfWeek.SUNDAY) Color.Red else PrimaryBlack
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                text = dayOfWeek.getDisplayName(java.time.format.TextStyle.NARROW, Locale.KOREAN),
                style = Typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                textAlign = TextAlign.Center,
                color = color
            )
        }
    }
}