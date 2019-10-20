package main.com.calendarapp.views.statistic

import io.reactivex.Observable
import main.com.calendarapp.models.Activeness
import main.com.calendarapp.models.Exercise
import main.com.calendarapp.repositories.ActivenessRepo
import main.com.calendarapp.repositories.ExerciseRepo
import main.com.calendarapp.util.rx.SchedulerProvider
import main.com.calendarapp.views.AbstractViewModel
import org.joda.time.DateTime

class StatisticsViewModel(
    val schedulerProvider: SchedulerProvider,
    private val activenessRepo: ActivenessRepo,
    private val exerciseRepo: ExerciseRepo
) : AbstractViewModel() {

    fun getAllActiveness(): Observable<List<Activeness>> {
        return activenessRepo.getAllActivenessesByYear(DateTime.now().year)
    }

    fun getAllExercises(): Observable<List<Exercise>> {
        return exerciseRepo.getAllExercises()
    }

}