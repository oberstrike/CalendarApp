package main.com.calendarapp.data

import main.com.calendarapp.models.Activeness

interface ActivenessRepo {
     fun getAllActivinesses(): Collection<Activeness>

     fun getActivinessById(id: Long) :Activeness
}