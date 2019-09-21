package main.com.calendarapp.models

import org.joda.time.DateTime


data class Activeness(val id: Long, val exercises:List<Exercise>?, val date:DateTime)