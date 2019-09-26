package main.com.calendarapp.views.activeness

import main.com.calendarapp.data.ActivenessRepo
import main.com.calendarapp.models.Activeness
import main.com.calendarapp.models.Exercise
import main.com.calendarapp.util.rx.SchedulerProvider
import main.com.calendarapp.views.AbstractViewModel

class ActivenessViewModel(
    val provider: SchedulerProvider,
    private val repository: ActivenessRepo
) : AbstractViewModel() {

    var activeness: Activeness? = null

    fun init(id: Long) {
        activeness = repository.getActivenessById(id).findFirst()
        if (activeness == null)
            return
    }

    fun save() {
        if (activeness != null) {
            repository.saveActiveness(activeness!!)
        } else {
            throw IllegalAccessError("Fehler in der ActivenessViewModel Klasse")
        }
    }

    fun addExercise() {
        if (activeness != null) {
            val exercise = Exercise(0, "Neues Training")
            activeness?.exercises?.add(exercise)
        }
    }


}