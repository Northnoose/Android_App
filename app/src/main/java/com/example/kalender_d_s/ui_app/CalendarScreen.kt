package com.example.kalender_d_s.ui_app

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRowScopeInstance.weight
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.kalender_d_s.viewmodel.CalendarViewModel
import java.time.LocalDate
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun CalendarScreen(year: Int,
                   month: Int,
                   viewModel: CalendarViewModel = viewModel()) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        Text("Calendar for $month $year")
        Spacer(modifier = Modifier.height(16.dp))
        WeekDaysHeader()
        CalendarGrid()
    }
}

@Composable
fun WeekDaysHeader() {
    Row(modifier = Modifier.fillMaxWidth()) {
        listOf("Man", "Tir", "Ons", "Tor", "Fre", "Lør", "Søn").forEach { day ->
            Text(text = day, modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun CalenderGrid(year: Int,
                 month: Int,
                 viewModel: CalendarViewModel = viewModel()) {
    val allDays = viewModel.getMonthDays(year, month)

}