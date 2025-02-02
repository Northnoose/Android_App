package com.example.kalender_d_s.ui_app

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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kalender_d_s.viewmodel.CalendarViewModel
import java.time.LocalDate
import java.time.Month


@Composable
fun CalendarScreen(initialYear: Int, viewModel: CalendarViewModel = viewModel()) {
    var currentYear by remember { mutableIntStateOf(initialYear) }
    var selectedDate: LocalDate? by remember { mutableStateOf(null) }
    var selectedMonth by remember { mutableStateOf<Int?>(null) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable {
                selectedDate = null
                selectedMonth = null
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
                items(12) { index ->
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
                            text = Month.of(currentMonth).name.lowercase().replaceFirstChar { it.uppercaseChar() },
                            style = MaterialTheme.typography.headlineMedium,
                            modifier = Modifier.weight(1f)
                        )

                        Button(
                            onClick = { showWorkDays = !showWorkDays },
                            modifier = Modifier.wrapContentSize(),
                            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 2.dp)
                        ) {
                            Text(text = if (showWorkDays) "Skjul Arbeidsdager" else "Vis Arbeidsdager")
                        }
                    }

                    if (showWorkDays) {
                        Text(
                            text = "Antall Arbeidsdager: ${viewModel.getWorkingDays(currentYear, currentMonth)}",
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
                            selectedMonth = month
                        }
                    )
                }
            }
        }
    }
}




