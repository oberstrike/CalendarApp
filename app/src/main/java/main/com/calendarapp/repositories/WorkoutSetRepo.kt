package main.com.calendarapp.repositories

import main.com.calendarapp.models.WorkoutSet

interface WorkoutSetRepo {

    fun saveWorkoutSet(workoutSet: WorkoutSet)

}