package main.com.calendarapp.views.main


import main.com.calendarapp.models.Activeness
import main.com.calendarapp.models.ActivenessType
import main.com.calendarapp.repositories.ActivenessRepo
import main.com.calendarapp.util.MainContext
import main.com.calendarapp.util.rx.SchedulerProvider
import main.com.calendarapp.views.AbstractViewModel
import org.joda.time.DateTime

class MainViewModel(
    private val repository: ActivenessRepo,
    val provider: SchedulerProvider
                   ) : AbstractViewModel(){

    init {
        MainContext.allActivenessObservable = repository.getAllActivenesses()
    }

    fun addActiveness(activenessType: ActivenessType) {
        val activeness = Activeness(0L, DateTime.now())
        activeness.type = activenessType
        repository.saveActiveness(activeness)
    }

    fun deleteAllActiveness() {
        repository.deleteAll()
    }

    fun getAllActiveness() = MainContext.allActivenessObservable

    fun deleteActiveness(activeness: Activeness): Boolean {
        repository.delete(activeness)
        return true
    }

    fun rename(activeness: Activeness, newName: String?): Boolean {
        if (newName != null) {
            if (newName.isNotEmpty()) {
                activeness.name = newName
                repository.saveActiveness(activeness)
                return true
            }
        }
        return false
    }


}