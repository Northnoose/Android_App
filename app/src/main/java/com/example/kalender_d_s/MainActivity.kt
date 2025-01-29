package com.example.kalender_d_s

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kalender_d_s.ui.theme.Kalender_D_STheme
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Kalender_D_STheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        CalendarHead(month = 1, year = 2025) // Korrekt kall
                        WeekDaysHeade() // Viser ukedager
                    }
                }
            }
        }
    }
}

@Composable
fun CalendarHead(month: Int, year: Int, modifier: Modifier = Modifier) {
    val monthName = LocalDate.of(year, month, 1)
        .month.getDisplayName(TextStyle.FULL, Locale.getDefault())

    Text(
        text = "$monthName $year",
        style = MaterialTheme.typography.headlineLarge,
        modifier = modifier.padding(16.dp)
    )
}

@Composable
fun WeekDaysHeade(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val weekDays = context.resources.getStringArray(R.array.weekdays_short)

    Row(modifier.fillMaxWidth()) {
        weekDays.forEach { day ->
            Text(
                text = day,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCalendar() {
    Kalender_D_STheme {
        Column {
            CalendarHead(month = 1, year = 2025)
            WeekDaysHeade()
        }
    }
}
