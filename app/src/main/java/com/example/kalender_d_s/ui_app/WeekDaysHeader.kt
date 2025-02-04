package com.example.kalender_d_s.ui_app

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.kalender_d_s.R

@Composable
fun WeekDaysHeader() {
    val weekdays = stringArrayResource(R.array.weekdays)


    Row(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        weekdays.forEachIndexed { index, day ->
            Text(
                text = day,
                modifier = Modifier.weight(if (index == 0) 0.8f else 1f),
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center
            )
        }
    }
}
