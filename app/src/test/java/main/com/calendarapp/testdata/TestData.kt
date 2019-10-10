package main.com.calendarapp.testdata

import main.com.calendarapp.models.Activeness
import main.com.calendarapp.models.Exercise
import main.com.calendarapp.models.WorkoutSet
import org.joda.time.DateTime

enum class TestData {

    TEST_1{
        override fun activeness(): List<Activeness>{
            val activeness = Activeness(0, DateTime.now())
            val exercise = Exercise(0, "Training")
            val workoutSet = WorkoutSet(0, 0, 0)
            exercise.workoutSets.add(workoutSet)
            activeness.exercises = listOf(exercise).toMutableList()
            return listOf(activeness)
        }
    };

    abstract fun activeness(): List<Activeness>

}