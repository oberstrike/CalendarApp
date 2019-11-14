package main.com.fitnesstracker.testdata

import main.com.fitnesstracker.models.Activeness
import main.com.fitnesstracker.models.ExerciseType
import main.com.fitnesstracker.util.MainContext

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