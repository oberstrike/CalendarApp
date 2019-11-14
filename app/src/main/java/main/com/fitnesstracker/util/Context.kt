package main.com.fitnesstracker.util

import io.reactivex.Observable
import main.com.fitnesstracker.models.Activeness
import main.com.fitnesstracker.models.Exercise
import main.com.fitnesstracker.models.ExerciseType
import main.com.fitnesstracker.models.WorkoutSet
import org.joda.time.DateTime

object MainContext {

    lateinit var allActivenessObservable: Observable<List<Activeness>>

    lateinit var activeActivenessObservable: Observable<List<Activeness>>

    var activeActiveness: Activeness? = null

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


