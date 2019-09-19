package main.com.calendarapp.data

import main.com.calendarapp.models.Appointment
import org.joda.time.DateTime

class AppointmentRepositoryImpl : AppointmentRepository{

    override fun getContent(): Collection<Appointment> {
        return listOf(Appointment("Prüfung", id = "1", date = DateTime(2018, 12, 12, 10, 10)))
    }
}