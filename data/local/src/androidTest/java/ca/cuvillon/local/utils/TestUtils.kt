package ca.cuvillon.local.utils

import java.util.Calendar
import java.util.Date

/**
 * Earliest [Date] object used for tests.
 */
val testEarliestDate: Date = Calendar.Builder()
    .setDate(2010, Calendar.NOVEMBER, 3)
    .setTimeOfDay(8, 34, 57, 10)
    .build()
    .time

/**
 * Latest [Date] object used for tests.
 */
val testLatestDate: Date = Calendar.getInstance().apply {
    time = testEarliestDate
    add(Calendar.YEAR, 1)
}.time
