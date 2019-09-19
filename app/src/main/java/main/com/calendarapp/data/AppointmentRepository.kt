package main.com.calendarapp.data

import main.com.calendarapp.models.Appointment

interface AppointmentRepository {
     fun getContent(): Collection<Appointment>
}