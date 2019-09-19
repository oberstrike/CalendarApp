package main.com.calendarapp.views.activeness

import io.reactivex.subjects.BehaviorSubject
import main.com.calendarapp.util.rx.SchedulerProvider
import main.com.calendarapp.views.AbstractViewModel

class ActivenessViewModel(val provider: SchedulerProvider): AbstractViewModel(){

    val subject: BehaviorSubject<String> = BehaviorSubject.create()

    override fun onCleared() {
        super.onCleared()
    }

    fun setName(name: String){
        subject.onNext(name)
    }
}