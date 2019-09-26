package main.com.calendarapp.ext

import org.joda.time.DateTime
import java.util.*

@SuppressWarnings
fun convertDateTimeToHeadline (dateTime: DateTime, language: Language = Language.DE) : String{
    val day = dateTime.dayOfMonth().get()
    val month = dateTime.monthOfYear().get()
    val dayMonth = "${if(day < 9) "0$day" else day}.${if(month < 9) "0$month" else month}."

    return when(language == Language.DE){
        true ->   "Training vom $dayMonth"
        false ->   "Training from $dayMonth"
    }
}

enum class Language {
    DE, EN
}