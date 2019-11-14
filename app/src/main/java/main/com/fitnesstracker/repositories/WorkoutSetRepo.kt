package main.com.fitnesstracker.repositories

import io.reactivex.Observable
import main.com.fitnesstracker.models.Exercise
import main.com.fitnesstracker.models.WorkoutSet

interface WorkoutSetRepo {
    fun saveWorkoutSet(workoutSet: WorkoutSet)
    fun getAllWorkoutSets(): Observable<List<WorkoutSet>>
    fun delete(x: WorkoutSet)
    fun getAllWorkoutSetsByExercise(exercise: Exercise): Observable<List<WorkoutSet>>
}