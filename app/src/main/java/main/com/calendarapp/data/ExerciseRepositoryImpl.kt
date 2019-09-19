package main.com.calendarapp.data

import main.com.calendarapp.models.Activeness

class ExerciseRepositoryImpl : ExerciseRepository {

    override fun getContent(): Collection<Activeness> {
        return listOf(Activeness(id = 1L, exercises = null))
    }
}