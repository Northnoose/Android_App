package com.example.kalender_d_s.ui_app

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun WeekDaysHeader() {
    Row(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        listOf("Uke","Man", "Tir", "Ons", "Tor", "Fre", "Lør", "Søn").forEach { day ->
            Text(text = day, modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.bodyLarge, // Tilpass tekststil
                textAlign = TextAlign.Center,
                )
        }
    }
}