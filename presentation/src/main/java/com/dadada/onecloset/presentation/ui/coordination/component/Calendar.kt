package com.dadada.onecloset.presentation.ui.coordination.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.dadada.onecloset.presentation.model.HorizontalCalendarConfig
import com.dadada.onecloset.presentation.ui.NavigationItem
import com.dadada.onecloset.presentation.ui.theme.PrimaryBlack
import com.dadada.onecloset.presentation.ui.theme.Typography
import com.dadada.onecloset.presentation.ui.utils.dateFormat
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.util.Locale

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HorizontalCalendar(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    currentDate: LocalDate = LocalDate.now(),
    config: HorizontalCalendarConfig = HorizontalCalendarConfig(),
    onSelectedDate: (LocalDate) -> Unit
) {
    val initialPage: Int =
        (currentDate.year - config.yearRange.first) * 12 + currentDate.monthValue - 1
    var currentSelectedDate by remember { mutableStateOf(currentDate) }
    var currentMonth: YearMonth by remember { mutableStateOf(YearMonth.now()) }
    var currentPage by remember { mutableStateOf(initialPage) }
    val pagerState = rememberPagerState(initialPage = initialPage)

    LaunchedEffect(pagerState.currentPage) {
        val addMonth = (pagerState.currentPage - currentPage).toLong()
        currentMonth = currentMonth.plusMonths(addMonth)
        currentPage = pagerState.currentPage
    }

    LaunchedEffect(currentSelectedDate) {
        onSelectedDate(currentSelectedDate)
    }

    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        val headerText = currentMonth.dateFormat("yyyy년 M월")
        val pageCount = (config.yearRange.last - config.yearRange.first) * 12
        CalendarHeader(
            modifier = Modifier.padding(20.dp), text = headerText
        )
        HorizontalPager(
            pageCount = pageCount, state = pagerState
        ) { page ->
            val date = LocalDate.of(
                config.yearRange.first + page / 12, page % 12 + 1, 1
            )
            if (page in pagerState.currentPage - 1..pagerState.currentPage + 1) { // 페이징 성능 개선을 위한 조건문
                CalendarMonthItem(modifier = Modifier.fillMaxWidth(),
                    currentDate = date,
                    selectedDate = currentSelectedDate,
                    onSelectedDate = { date ->
                        currentSelectedDate = date
                    }, navController = navController)
            }
        }
    }
}

@Composable
fun CalendarHeader(
    modifier: Modifier = Modifier,
    text: String,
) {
    Box(modifier = modifier) {
        Text(
            text = text, style = Typography.titleLarge.copy(fontWeight = FontWeight.ExtraBold)
        )
    }
}

@Composable
fun CalendarMonthItem(
    modifier: Modifier = Modifier,
    currentDate: LocalDate,
    selectedDate: LocalDate,
    onSelectedDate: (LocalDate) -> Unit,
    navController: NavHostController
) {
    val lastDay by remember { mutableStateOf(currentDate.lengthOfMonth()) }
    val firstDayOfWeek by remember { mutableStateOf(currentDate.dayOfWeek.value) }
    val days by remember { mutableStateOf(IntRange(1, lastDay).toList()) }

    Column(modifier = modifier) {
        DayOfWeek()
        LazyVerticalGrid(
            modifier = Modifier.fillMaxHeight(), columns = GridCells.Fixed(7)
        ) {
            if(firstDayOfWeek < 7) {
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
                    navController
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
    navController: NavHostController
) {
    val hasEvent = false
    Box(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(0.7f)
            .clip(shape = RoundedCornerShape(10.dp))
            .clickable {
                onSelectedDate(date)
                navController.navigate(NavigationItem.CoordinationResultNav.route)
            },
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth()
                .aspectRatio(1f)
                .clip(CircleShape),
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(model = "", contentDescription = "", contentScale = ContentScale.Crop)
        }


        Text(
            modifier = Modifier.padding(4.dp),
            textAlign = TextAlign.Center,
            text = date.dayOfMonth.toString(),
            fontWeight = FontWeight.Bold,
            color = Color.White
        )

        if (hasEvent) {
            Box(
                modifier = Modifier
                    .size(4.dp)
                    .clip(shape = RoundedCornerShape(4.dp))
            )
        }
    }
}

@Composable
fun DayOfWeek(
    modifier: Modifier = Modifier
) {
    Row(modifier) {
        val reorderedDays = listOf(DayOfWeek.SUNDAY) + DayOfWeek.values().filter { it != DayOfWeek.SUNDAY }
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