package com.example.kalender_d_s

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.kalender_d_s.ui_app.CalendarScreen
import com.example.kalender_d_s.viewmodel.CalendarViewModel
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CalendarUITest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testDaysSinceJanDialog() {

        val viewModel = CalendarViewModel()

        composeTestRule.setContent {
            CalendarScreen(initialYear = 2025, initialMonth = 1)
        }

        val expectedDaysSinceJan = viewModel.getSinceJan(2025, 1, 15)

        composeTestRule.onAllNodes(hasText("15")).printToLog("TestLog")

        composeTestRule.onAllNodes(hasText("15"))
            .onFirst()
            .assertIsDisplayed()
            .performClick()

        composeTestRule
            .onNodeWithText("$expectedDaysSinceJan dager siden 1. januar")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("$expectedDaysSinceJan dager siden 1. januar")
            .performClick()

        composeTestRule
            .onNodeWithText("$expectedDaysSinceJan dager siden 1. januar")
            .assertDoesNotExist()

    }
}
