package main.com.calendarapp.data

import main.com.calendarapp.models.Activeness

interface Datasource {
    fun getAllActiveness() : ArrayList<Activeness>

    fun addActiveness(activeness: Activeness)

    fun deleteAll()
}