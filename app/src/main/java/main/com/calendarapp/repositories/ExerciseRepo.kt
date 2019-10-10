package main.com.calendarapp.repositories

import io.reactivex.Observable
import main.com.calendarapp.models.Exercise

interface ExerciseRepo {
    fun getAllExercises(): Observable<List<Exercise>>

    fun getExerciseById(id:Long):Observable<List<Exercise>>

    fun saveExercise(exercise: Exercise)
}