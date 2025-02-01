package com.example.kalender_d_s.ui_app

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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



@Composable
fun CalendarScreen(year: Int,
                   month: Int,
                   viewModel: CalendarViewModel = viewModel()) {
    var monthsToShow by remember { mutableIntStateOf(1) }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {

        val monthName = Month.of(month).name.lowercase().replaceFirstChar { it.uppercaseChar() }
        Text(
            "Calendar $year",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

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






