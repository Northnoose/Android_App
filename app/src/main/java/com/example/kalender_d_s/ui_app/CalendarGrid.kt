package com.example.kalender_d_s.ui_app

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kalender_d_s.R
import com.example.kalender_d_s.viewmodel.CalendarViewModel
import java.time.LocalDate
import java.time.temporal.IsoFields

@Composable
fun CalendarGrid(
    year: Int,
    month: Int,
    viewModel: CalendarViewModel = viewModel(),
    selectedDate: LocalDate?,
    selectedMonth: Int?,
    onDateSelected: (LocalDate?, Int?) -> Unit
) {
    val allDays = viewModel.getMonthDays(year, month)
    val startOfMonth = viewModel.getStartOfMonth(year, month)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        val emptyStartDays = startOfMonth - 1
        val totalDays = emptyStartDays + allDays.size

        val gridDays = List(totalDays) { index ->
            if (index < emptyStartDays) null else allDays[index - emptyStartDays]
        }

        gridDays.chunked(7).forEach { week ->
            Row(modifier = Modifier.fillMaxWidth()) {

                Box( //Dette er Ukenummrene
                    modifier = Modifier
                        .weight(1f)
                        .padding(2.dp)
                ){
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    )
                    {
                        val isSelectedWeek = selectedDate != null && week.contains(selectedDate)
                        Text(
                            text = "${
                                week.firstOrNull { it != null }
                                    ?.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR) ?: "?"
                            }",
                            color = if(isSelectedWeek) Color.Red else Color.Black,
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontWeight = if (isSelectedWeek) FontWeight.Bold else FontWeight.Normal
                            ),
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }

                week.forEach { day ->
                    if (day == null) {
                        Spacer(modifier = Modifier
                            .weight(1f)
                            .padding(4.dp))
                    } else {
                        val isWeekend = day.dayOfWeek.value in 6..7
                        val isSelected = selectedDate == day && selectedMonth == month
                        val animatedScale by animateFloatAsState(
                            targetValue = if (isSelected) 1.5f else 1f,
                            animationSpec = tween(durationMillis = 300),
                            label = "scale"
                        )

                        ElevatedCard(
                            modifier = Modifier
                                .weight(1f)
                                .padding(4.dp)
                                .scale(animatedScale)
                                .zIndex(if (isSelected) 1f else 0f)
                                .clickable {
                                    if (isSelected) {
                                        onDateSelected(null, null)
                                    } else {
                                        onDateSelected(day, month)
                                    }
                                },
                            colors = CardDefaults.elevatedCardColors(
                                if (isWeekend) MaterialTheme.colorScheme.primaryContainer
                                else MaterialTheme.colorScheme.surface
                            )
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize(),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = day.dayOfMonth.toString(),
                                    modifier = Modifier.padding(8.dp),
                                )
                            }
                        }
                    }
                }
                val emptyDays = 7 - week.size
                repeat(emptyDays) {
                    Spacer(modifier = Modifier
                        .weight(1f)
                        .padding(4.dp))
                }
            }
        }


        if (selectedDate != null && selectedMonth == month) {
            val daysSinceJan = viewModel.getSinceJan(
                selectedDate.year,
                selectedDate.monthValue,
                selectedDate.dayOfMonth
            )

            AnimatedVisibility(
                visible = true,
                enter = fadeIn(animationSpec = tween(300)) + slideInVertically(initialOffsetY = { it / 2 }),
                exit = fadeOut(animationSpec = tween(300)) + slideOutVertically(targetOffsetY = { it / 2 })
            ) {
                Surface(
                    shape = MaterialTheme.shapes.medium,
                    color = MaterialTheme.colorScheme.primaryContainer,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 5.dp, vertical = 4.dp)
                        .clickable { onDateSelected(null, null) }
                ) {
                    Text(
                        text = stringResource(R.string.days_since_jan, daysSinceJan),
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(8.dp),
                        textAlign = TextAlign.Center,
                        maxLines = 1
                    )
                }
            }
        }
    }
}

