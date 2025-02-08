package com.example.kalender_d_s.viewmodel

import androidx.lifecycle.ViewModel
import com.example.kalender_d_s.model.CalendarData
import java.time.LocalDate

class CalendarViewModel : ViewModel(){
    fun getMonthDays(year: Int, month: Int): List<LocalDate> {
        return CalendarData.daysPerMonth(year, month)
    }

    fun getStartOfMonth(year: Int, month: Int) : Int {
        return CalendarData.startOfMonth(year, month)
    }

    fun getWorkingDays(year: Int, month: Int) : Int {
        return CalendarData.workDaysPerMonth(year, month)
    }

    fun getSinceJan(year: Int, month: Int, day: Int) : Long {
        return CalendarData.daysSinceFirstJan(year, month, day)
    }
}