package ca.cuvillon.repository.utils

import java.util.Date

/**
 * Mockable current [Date] for testing purposes.
 */
interface DateNow {
    fun get(): Date
}

class DateNowImpl : DateNow {
    override fun get(): Date {
        return Date()
    }
}
