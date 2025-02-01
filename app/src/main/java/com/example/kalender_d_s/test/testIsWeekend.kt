package com.example.kalender_d_s.test

import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import org.junit.Test
import java.time.DayOfWeek
import java.time.LocalDate

@Test
fun testIsWeekend(): Unit {
    val saturday = LocalDate.of(2025, 1, 4) // Lørdag
    val sunday = LocalDate.of(2025, 1, 5)   // Søndag
    val monday = LocalDate.of(2025, 1, 6)   // Mandag

    assertTrue(saturday.dayOfWeek == DayOfWeek.SATURDAY)
    assertTrue(sunday.dayOfWeek == DayOfWeek.SUNDAY)
    assertFalse(monday.dayOfWeek == DayOfWeek.SATURDAY || monday.dayOfWeek == DayOfWeek.SUNDAY)
}
