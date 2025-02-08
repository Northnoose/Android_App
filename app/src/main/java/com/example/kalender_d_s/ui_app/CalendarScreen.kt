package com.example.kalender_d_s.ui_app

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kalender_d_s.R
import com.example.kalender_d_s.viewmodel.CalendarViewModel
import java.time.LocalDate

@Composable
fun CalendarScreen(initialYear: Int, initialMonth: Int, viewModel: CalendarViewModel = viewModel()) {
    var currentYear by remember { mutableIntStateOf(initialYear) }
    var selectedDate: LocalDate? by remember { mutableStateOf(null) }
    var selectedMonth by remember { mutableIntStateOf(initialMonth) }


    val months = stringArrayResource(R.array.months)


    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable {
                selectedDate = null
            }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(shape = CircleShape, color = MaterialTheme.colorScheme.primaryContainer, modifier = Modifier.size(48.dp)) {
                    IconButton(onClick = { currentYear-- }) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Forrige år", tint = Color.Black)
                    }
                }

                Text(
                    text = "$currentYear",
                    style = MaterialTheme.typography.headlineLarge,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(1f).padding(32.dp)
                )

                Surface(shape = CircleShape, color = MaterialTheme.colorScheme.primaryContainer, modifier = Modifier.size(48.dp)) {
                    IconButton(onClick = { currentYear++ }) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowForward, contentDescription = "Neste år", tint = Color.Black)
                    }
                }
            }


            LazyColumn {
                items(12) { index -> // Vis alle måneder på samme skjerm.
                    val currentMonth = index + 1
                    var showWorkDays by remember { mutableStateOf(false) }
                    Spacer(modifier = Modifier.height(16.dp))


                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = months[currentMonth - 1],
                            style = MaterialTheme.typography.headlineMedium,
                            modifier = Modifier.weight(1f)
                        )

                        Button(
                            onClick = { showWorkDays = !showWorkDays },
                            modifier = Modifier.wrapContentSize(),
                            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 2.dp)
                        ) {
                            Text(text = stringResource(if (showWorkDays) R.string.hide_workdays else R.string.show_workdays))
                        }
                    }


                    if (showWorkDays) {
                        Text(
                            text = stringResource(R.string.num_workdays, viewModel.getWorkingDays(currentYear, currentMonth)),
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(vertical = 1.dp, horizontal = 18.dp)
                        )
                    }

                    WeekDaysHeader()
                    CalendarGrid(
                        year = currentYear,
                        month = currentMonth,
                        viewModel = viewModel,
                        selectedDate = selectedDate,
                        selectedMonth = selectedMonth,
                        onDateSelected = { date, month ->
                            selectedDate = date
                            if (month != null) {
                                selectedMonth = month
                            }
                        }
                    )
                }
            }
        }
    }
}
