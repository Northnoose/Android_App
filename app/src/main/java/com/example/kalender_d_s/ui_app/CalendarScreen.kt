package com.example.kalender_d_s.ui_app

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kalender_d_s.viewmodel.CalendarViewModel
import java.time.Month

// comment12
@Composable
fun CalendarScreen(initialYear: Int, viewModel: CalendarViewModel = viewModel()) {
    var currentYear by remember { mutableIntStateOf(initialYear) }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(onClick = {currentYear--}) {
            Text(text = "Forrige år")
        }
        Text(
            text = "Kalender for $currentYear",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(8.dp),
            textAlign = TextAlign.Center
        )
        Button(onClick = {currentYear++}) {
            Text(text = "Neste år")
        }
    }




        Spacer(modifier = Modifier.height(16.dp))



        LazyColumn {
            items(12) { index ->
                val currentMonth = index + 1
                Spacer(modifier = Modifier.height(3.dp))
                Text(
                    text = Month.of(currentMonth).name.lowercase().replaceFirstChar { it.uppercaseChar() },
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(start = 21.dp)

                )

                    WeekDaysHeader()
                    CalendarGrid(currentYear, currentMonth, viewModel)
                }
            }
        }


    }





