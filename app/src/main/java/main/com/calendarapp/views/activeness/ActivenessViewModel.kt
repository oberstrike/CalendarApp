package main.com.calendarapp.views.activeness

import com.google.gson.Gson
import io.reactivex.subjects.BehaviorSubject
import main.com.calendarapp.data.ActivenessRepo
import main.com.calendarapp.data.local.FileManager
import main.com.calendarapp.util.rx.SchedulerProvider
import main.com.calendarapp.views.AbstractViewModel

class ActivenessViewModel(val provider: SchedulerProvider,
                          val repository: ActivenessRepo,
                          val fileManager: FileManager): AbstractViewModel(){

    val subject: BehaviorSubject<String> = BehaviorSubject.create()

    fun onStart(name: String){
        subject.onNext(name)
    }

}