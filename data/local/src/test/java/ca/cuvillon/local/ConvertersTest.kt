package ca.cuvillon.local

import org.junit.Assert
import org.junit.Test
import java.util.Calendar

class ConvertersTest {

    private val date = Calendar.getInstance().apply {
        set(Calendar.YEAR, 2010)
        set(Calendar.MONTH, Calendar.NOVEMBER)
        set(Calendar.DAY_OF_MONTH, 3)
        set(Calendar.HOUR_OF_DAY, 8)
        set(Calendar.MINUTE, 34)
        set(Calendar.SECOND, 57)
        set(Calendar.MILLISECOND, 10)
    }.time

    @Test
    fun dateToTimestamp() {
        Assert.assertEquals(date.time, Converters().dateToTimestamp(date))
    }

    @Test
    fun fromTimestamp() {
        Assert.assertEquals(Converters().fromTimestamp(date.time), date)
    }
}
