package com.example.kalender_d_s

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.kalender_d_s.ui.theme.Kalender_D_STheme
import androidx.compose.ui.unit.dp
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Kalender_D_STheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CalendarHead(
                        day = 1,
                        month = "Januar",
                        year = 1996,
                        modifier = Modifier.padding(innerPadding),

                    )
                }
            }
        }
    }
}

@Composable
fun CalendarHead(month: Int, year: Int) {
    val monthName = LocalDate.of(year, month, 1)
        .month.getDisplayName(TextStyle.FULL, Locale.getDefault())
    Text(
        text = "$monthName $year"
        style = MaterialTheme.typography.headlineLarge,
        modifier = modifier.padding(16.dp)
    )
}

@Composable
fun WeekDaysHeader() {
    val weekDays = context.resources.getStringArray(R.array.weekdays_short)
    Row(Modifier.fillMaxWidth()) {
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
fun GreetingPreview() {
    Kalender_D_STheme {
        CalendarHead(day = 1, month = "Januar", year = 1996)
    }
}