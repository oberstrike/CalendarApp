package main.com.calendarapp.util

import io.reactivex.Observable
import main.com.calendarapp.models.Activeness
import main.com.calendarapp.models.Exercise
import main.com.calendarapp.models.ExerciseType
import main.com.calendarapp.models.WorkoutSet
import org.joda.time.DateTime

object MainContext {

    lateinit var allActivenessObservable: Observable<List<Activeness>>

    lateinit var activeActivenessObservable: Observable<List<Activeness>>

    var settings: Settings = Settings(FilterType.NAME, 0)

    fun createActiveness() = Activeness(0, DateTime.now())

    fun createExercise(exerciseType: ExerciseType) = Exercise(0, "Übung", exerciseType)

    fun createWorkoutSet() = WorkoutSet(0, 0, 0.0f)

}

object ActivenessContext {

    lateinit var allExerciseObservable: Observable<List<Exercise>>

    lateinit var activeExerciseObservable: Observable<List<Exercise>>

    lateinit var activeExercise: Exercise
}

object ExerciseContext {
    lateinit var workoutSets: Observable<List<WorkoutSet>>

}


