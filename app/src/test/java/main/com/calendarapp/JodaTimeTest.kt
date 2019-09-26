package main.com.calendarapp


import main.com.calendarapp.ext.convertDateTimeToHeadline
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormatterBuilder
import org.junit.Test

import org.junit.Assert.*

class JodaTimeTest {

    @Test
    fun convertDateTimeToHeadlineTest(){
        val formatter = DateTimeFormatterBuilder().appendPattern("dd.MM.yyyy").toFormatter()

        assertEquals("Training vom 15.05", convertDateTimeToHeadline(DateTime.parse("15.05.2019", formatter)))
    }

}