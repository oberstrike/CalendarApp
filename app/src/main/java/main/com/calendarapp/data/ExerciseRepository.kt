package main.com.calendarapp.data

import main.com.calendarapp.models.Activeness

interface ExerciseRepository {
     fun getContent(): Collection<Activeness>
}