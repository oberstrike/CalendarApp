package main.com.calendarapp.util

import io.reactivex.Observable
import main.com.calendarapp.models.Activeness
import main.com.calendarapp.models.Exercise
import main.com.calendarapp.models.WorkoutSet

object MainContext {

    lateinit var allActivenessObservable: Observable<List<Activeness>>

    lateinit var activeActivenessObservable: Observable<List<Activeness>>

    var settings: Settings = Settings(FilterType.NAME)

}

object ActivenessContext {

    lateinit var allExerciseObservable: Observable<List<Exercise>>

    lateinit var activeExerciseObservable: Observable<List<Exercise>>

    lateinit var activeExercise: Exercise
}

object ExerciseContext {
    lateinit var workoutSets: Observable<List<WorkoutSet>>

}


