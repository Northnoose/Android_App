package com.example.kalender_d_s

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.kalender_d_s.ui_app.CalendarBackground
import com.example.kalender_d_s.viewmodel.CalendarViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PreviewCalendarScreen()
        }

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCalendarScreen() {
    CalendarBackground(initialYear = 2025, viewModel = CalendarViewModel())
}

// comment12