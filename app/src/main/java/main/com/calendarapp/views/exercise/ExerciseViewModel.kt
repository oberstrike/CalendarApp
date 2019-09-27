package main.com.calendarapp.views.exercise

import main.com.calendarapp.repositories.ActivenessRepo
import main.com.calendarapp.util.rx.SchedulerProvider
import main.com.calendarapp.views.AbstractViewModel

class ExerciseViewModel(val activenessRepo: ActivenessRepo,
                        val schedulerProvider: SchedulerProvider) : AbstractViewModel() {

}