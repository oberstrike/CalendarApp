package main.com.calendarapp.repositories

import io.reactivex.Observable
import main.com.calendarapp.models.WorkoutSet

interface WorkoutSetRepo {
    fun saveWorkoutSet(workoutSet: WorkoutSet)
    fun getAllWorkoutSets(): Observable<List<WorkoutSet>>
    fun delete(x: WorkoutSet)
}