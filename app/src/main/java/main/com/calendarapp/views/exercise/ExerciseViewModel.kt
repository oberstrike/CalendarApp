package main.com.calendarapp.views.exercise

import io.objectbox.query.Query
import main.com.calendarapp.models.Activeness
import main.com.calendarapp.models.Exercise
import main.com.calendarapp.repositories.ActivenessRepo
import main.com.calendarapp.util.rx.SchedulerProvider
import main.com.calendarapp.views.AbstractViewModel

class ExerciseViewModel(val repository: ActivenessRepo,
                        val schedulerProvider: SchedulerProvider) : AbstractViewModel() {
    var activeness: Activeness? = null
    var exercise: Exercise? = null

    fun init(activenessId: Int, exerciseId: Int, setCount: Int) {
        val activeness = repository.getActivenessById(activenessId.toLong()).findFirst()

        if(activeness != null){
            if(activeness.exercises != null){
                if(activeness.exercises.size < exerciseId){
                    val exercise = activeness.exercises[exerciseId.toInt()]
                    //TODO

                }
            }
            this.activeness = activeness
        }
    }

    fun saveActiveness(){
        if(activeness != null)
            repository.saveActiveness(activeness!!)

    }
}