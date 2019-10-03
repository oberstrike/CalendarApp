package main.com.calendarapp.views.activeness

import io.objectbox.query.Query
import main.com.calendarapp.repositories.ActivenessRepo
import main.com.calendarapp.models.Activeness
import main.com.calendarapp.models.Exercise
import main.com.calendarapp.util.rx.SchedulerProvider
import main.com.calendarapp.views.AbstractViewModel

class ActivenessViewModel(
    val provider: SchedulerProvider,
    private val repository: ActivenessRepo
) : AbstractViewModel() {

    lateinit var activeness: Query<Activeness>

    fun init(id: Long) {
        activeness = repository.getActivenessById(id)
        if (activeness == null)
            return
    }


    fun addExercise(): Int {
        if (activeness != null) {
            val lExercise = Exercise(0, "Neues Training")
            val lActiv = activeness.findFirst()
            if(lActiv != null){
                lActiv.exercises.add(lExercise)
                repository.saveActiveness(lActiv)
                return lActiv.exercises.size
            }
        }
        return 1
    }


}