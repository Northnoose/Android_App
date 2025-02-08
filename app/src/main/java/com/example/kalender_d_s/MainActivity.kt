package com.example.kalender_d_s

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.kalender_d_s.ui_app.CalendarBackground
import com.example.kalender_d_s.viewmodel.CalendarViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val initialYear = 2025
        val initialMonth = 1
        val viewModel = CalendarViewModel()

        setContent {
            CalendarBackground(initialYear = initialYear, initialMonth = initialMonth, viewModel = viewModel)
        }
    }
}
