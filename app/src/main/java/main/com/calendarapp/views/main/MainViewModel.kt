package main.com.calendarapp.views.main


import io.objectbox.query.Query
import main.com.calendarapp.data.ActivenessRepo
import main.com.calendarapp.models.Activeness
import main.com.calendarapp.util.rx.SchedulerProvider
import main.com.calendarapp.views.AbstractViewModel
import org.joda.time.DateTime

class MainViewModel(val repository: ActivenessRepo,
                    val provider: SchedulerProvider
                   ) : AbstractViewModel(){

    val activities: Query<Activeness> = repository.getAllActivenesses()

    fun addActiveness(){
       repository.saveActiveness(Activeness(0L, DateTime.now()))
    }

    fun deleteActiveness(){
        repository.deleteAll()
    }
}