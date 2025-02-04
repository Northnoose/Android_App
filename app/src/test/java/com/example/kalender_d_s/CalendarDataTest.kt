package com.example.kalender_d_s


import com.example.kalender_d_s.model.CalendarData
import org.junit.Assert.assertEquals
import java.time.LocalDate
import org.junit.Test as Test1

class CalendarDataTest {

    @Test1
    fun testWorkDaysPerMonth() {

        val workdays = CalendarData.WorkDaysPerMonth(2025, 1)
        assertEquals(23, workdays)


        val workdaysFeb = CalendarData.WorkDaysPerMonth(2025, 2)
        assertEquals(20, workdaysFeb)


        val workdaysMarch = CalendarData.WorkDaysPerMonth(2025, 3)
        assertEquals(21, workdaysMarch)
    }

    @Test1
    fun testDaysSinceFirstJan() {

        assertEquals(0, CalendarData.DaysSinceFirstJan(2025, 1, 1))


        assertEquals(9, CalendarData.DaysSinceFirstJan(2025, 1, 10))


        assertEquals(31, CalendarData.DaysSinceFirstJan(2025, 2, 1))


        assertEquals(364, CalendarData.DaysSinceFirstJan(2025, 12, 31))
    }
}
