package main.com.calendarapp.util

import io.reactivex.Observable
import main.com.calendarapp.models.Activeness
import main.com.calendarapp.models.Exercise

object MainContext {

    lateinit var allActivenessObservable: Observable<List<Activeness>>

    lateinit var activeActivenessObservable: Observable<List<Activeness>>

}

object ExerciseContext {

    lateinit var allExerciseObservable: Observable<List<Exercise>>

    lateinit var activeExerciseObservable: Observable<List<Exercise>>

}