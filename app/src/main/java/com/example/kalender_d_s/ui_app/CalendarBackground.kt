package com.example.kalender_d_s.ui_app

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.kalender_d_s.R
import com.example.kalender_d_s.viewmodel.CalendarViewModel

@Composable
fun CalendarBackground(initialYear: Int, viewModel: CalendarViewModel) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.white_background),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        CalendarScreen(initialYear, viewModel)
    }
}