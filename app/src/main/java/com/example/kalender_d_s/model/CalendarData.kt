package com.example.kalender_d_s.model

import java.time.LocalDate
import java.time.YearMonth
import java.time.temporal.ChronoUnit

object CalendarData {
    fun daysPerMonth(year: Int, month: Int): List<LocalDate> {
        val days = YearMonth.of(year, month).lengthOfMonth()
        return (1..days).map {day -> LocalDate.of(year, month, day)}
    }
    fun startOfMonth(year: Int, month: Int) : Int {
        return LocalDate.of(year, month, 1).dayOfWeek.value
    }

    fun workDaysPerMonth(year: Int, month: Int) : Int {
        return daysPerMonth(year, month).count {it.dayOfWeek.value in 1..5}
    }

    fun daysSinceFirstJan(year: Int, month: Int, day: Int) : Long {
        val startOfYear = LocalDate.of(year, 1, 1)
        return ChronoUnit.DAYS.between(startOfYear, LocalDate.of(year, month, day))
    }
}