package main.com.calendarapp.testdata

import main.com.calendarapp.models.Activeness
import main.com.calendarapp.models.ExerciseType
import main.com.calendarapp.util.MainContext

enum class TestData {

    ACTIVENESS_1 {
        override fun activeness(): List<Activeness>{
            val activeness = MainContext.createActiveness()
            val exercise = MainContext.createExercise(ExerciseType.STRENGTHWORKOUTSET)
            val workoutSet = MainContext.createWorkoutSet()
            exercise.workoutSets.add(workoutSet)
            activeness.exercises = listOf(exercise).toMutableList()
            return listOf(activeness)
        }
    };

    abstract fun activeness(): List<Activeness>

}