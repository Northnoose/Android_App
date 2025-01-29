package com.example.kalender_d_s.viewmodel

import androidx.lifecycle.ViewModel
import com.example.kalender_d_s.model.CalendarData
import java.time.LocalDate

class CalendarViewModel : ViewModel(){
    fun getMonthDays(year: Int, month: Int): List<LocalDate> {
        return CalendarData.DaysPerMonth(year, month)
    }

    fun getStartOfMonth(year: Int, month: Int) : Int {
        return CalendarData.StartOfMonth(year, month)
    }

    fun getWorkingDays(year: Int, month: Int) : Int {
        return CalendarData.WorkDaysPerMonth(year, month)
    }

    fun getSinceJan(year: Int, month: Int, day: Int) : Long {
        return CalendarData.DaysSinceFirstJan(year, month, day)
    }
}