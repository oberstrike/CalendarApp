package main.com.calendarapp.views.main

import io.reactivex.subjects.BehaviorSubject
import main.com.calendarapp.data.ExerciseRepository
import main.com.calendarapp.models.Activeness
import main.com.calendarapp.util.rx.SchedulerProvider
import main.com.calendarapp.views.AbstractViewModel

class MainViewModel(private val repository: ExerciseRepository, val provider: SchedulerProvider) : AbstractViewModel(){

    val activities: BehaviorSubject<List<Activeness>> = BehaviorSubject.create()

    fun onLoad():Unit {
        activities.onNext(repository.getContent().toList())
    }

}