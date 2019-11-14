package main.com.fitnesstracker.repositories

import io.reactivex.Observable
import main.com.fitnesstracker.models.Exercise

interface ExerciseRepo {
    fun getAllExercises(): Observable<List<Exercise>>

    fun getExerciseById(id:Long):Observable<List<Exercise>>

    fun saveExercise(exercise: Exercise)

    fun deleteExercise(exercise: Exercise)
}