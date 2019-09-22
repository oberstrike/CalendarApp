package main.com.calendarapp.views.main


import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import main.com.calendarapp.data.ActivenessRepo
import main.com.calendarapp.data.local.FileManager
import main.com.calendarapp.models.Activeness
import main.com.calendarapp.util.rx.SchedulerProvider
import main.com.calendarapp.views.AbstractViewModel
import org.joda.time.DateTime
import java.util.*
import kotlin.collections.ArrayList

class MainViewModel(val repository: ActivenessRepo,
                    val provider: SchedulerProvider,
                    val fileManager: FileManager
                   ) : AbstractViewModel(){

    val activities: Observable<Collection<Activeness>> = repository.getAllActivenesses()

    fun addActiveness(){
       repository.saveActiveness(Activeness(10L, null, DateTime.now() ))
    }

    fun init(){

    }
}