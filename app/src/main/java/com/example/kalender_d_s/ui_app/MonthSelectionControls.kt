package com.example.kalender_d_s.ui_app

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MonthSelectionControls(
    monthsToShow: Int,
    onMonthsChange: (Int) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        horizontalArrangement = Arrangement.spacedBy(43.dp, Alignment.CenterHorizontally),
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
