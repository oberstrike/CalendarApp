package main.com.fitnesstracker.views.statistic

import io.reactivex.Observable
import main.com.fitnesstracker.models.Activeness
import main.com.fitnesstracker.models.Exercise
import main.com.fitnesstracker.repositories.ActivenessRepo
import main.com.fitnesstracker.repositories.ExerciseRepo
import main.com.fitnesstracker.util.rx.SchedulerProvider
import main.com.fitnesstracker.views.AbstractViewModel
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