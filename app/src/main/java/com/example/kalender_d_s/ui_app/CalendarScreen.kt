package com.example.kalender_d_s.ui_app

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.kalender_d_s.viewmodel.CalendarViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import java.time.Month
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment


@Composable
fun CalendarScreen(year: Int,
                   month: Int,
                   viewModel: CalendarViewModel = viewModel()) {
    var monthsToShow by remember { mutableIntStateOf(1) }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {

        val monthName = Month.of(month).name.lowercase().replaceFirstChar { it.uppercaseChar() }
        Text("Calendar $year", style = MaterialTheme.typography.headlineLarge, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)

        Spacer(modifier = Modifier.height(16.dp))

        MonthSelectionControls(
            monthsToShow = monthsToShow,
            onMonthsChange = {monthsToShow = it}
        )


        Spacer(modifier = Modifier.height(16.dp))



        LazyColumn {
            items(monthsToShow) { i ->
                val currentMonth = (month - 1 + i) % 12 + 1
                val currentYear = year + (month - 1 + i) / 12

                key("$currentMonth-$currentYear") {


                    Spacer(modifier = Modifier.height(3.dp))
                    Text(
                        "${Month.of(currentMonth).name.lowercase().replaceFirstChar { it.uppercaseChar() }} ",
                        style = MaterialTheme.typography.headlineMedium,
                        modifier = Modifier.padding(start = 21.dp)

                    )

                    WeekDaysHeader()
                    CalendarGrid(currentYear, currentMonth, viewModel)
                }
            }
        }


    }
}

@Composable
fun MonthSelectionControls(
    monthsToShow: Int,
    onMonthsChange: (Int) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        horizontalArrangement = Arrangement.spacedBy(43.dp,Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically) {

        Button(onClick = {if (monthsToShow > 1) onMonthsChange(monthsToShow - 1)}) {
            Text("-")
        }
        Text(
            "Viser $monthsToShow måned(er)",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.align(Alignment.CenterVertically))
        Button(onClick = { if (monthsToShow < 3) onMonthsChange(monthsToShow + 1)}) {
            Text("+")
        }
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
    Log.d("CalendarGrid", "Tegner kalender for: $month $year")
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

