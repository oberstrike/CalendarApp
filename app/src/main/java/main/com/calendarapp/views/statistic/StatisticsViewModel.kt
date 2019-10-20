package main.com.calendarapp.views.statistic

import io.reactivex.Observable
import main.com.calendarapp.models.Activeness
import main.com.calendarapp.repositories.ActivenessRepo
import main.com.calendarapp.util.rx.SchedulerProvider
import main.com.calendarapp.views.AbstractViewModel
import org.joda.time.DateTime

class StatisticsViewModel(
    val schedulerProvider: SchedulerProvider,
    private val activenessRepo: ActivenessRepo
) : AbstractViewModel() {

    fun getAllActiveness(): Observable<List<Activeness>> {
        return activenessRepo.getAllActivenessesByYear(DateTime.now().year)
    }

}