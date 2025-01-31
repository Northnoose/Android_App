package com.example.kalender_d_s.ui_app

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kalender_d_s.viewmodel.CalendarViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import java.time.Month

@Composable
fun CalendarScreen(year: Int,
                   month: Int,
                   viewModel: CalendarViewModel = viewModel()) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        val monthName = Month.of(month).name.lowercase().replaceFirstChar { it.uppercaseChar() }
        Text("Calendar for $monthName $year")
        Spacer(modifier = Modifier.height(16.dp))
        WeekDaysHeader()
        CalendarGrid(year, month, viewModel)
    }
}

@Composable
fun WeekDaysHeader() {
    Row(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        listOf("Man", "Tir", "Ons", "Tor", "Fre", "Lør", "Søn").forEach { day ->
            Text(text = day, modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.bodyLarge, // Tilpass tekststil
                textAlign = TextAlign.Center)
        }
    }
}

@Composable
fun CalendarGrid(year: Int,
                 month: Int,
                 viewModel: CalendarViewModel = viewModel()) {
    val allDays = viewModel.getMonthDays(year, month)
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        allDays.chunked(7).forEach { week -> //Her kaller jeg chunked(7) som deler lista opp i 7 dager
            Row(modifier = Modifier.fillMaxWidth()) {
                week.forEach {day -> //Gjør det samme her, deler uka opp in dager å kaster det inn i en Lambda funksjon
                    ElevatedCard(modifier = Modifier
                            .weight(1f)
                            .padding(4.dp)){
                        Text(text = day.dayOfMonth.toString(),
                            modifier = Modifier.padding(8.dp)) //Gjør om formatet 2025-1-3 til "3"
                    }
                }

                val emptyDays = 7 - week.size
                repeat(emptyDays) {
                    Spacer(
                        modifier = Modifier
                            .weight(1f)
                            .padding(4.dp)
                    )
                }

            }
        }
    }
}

