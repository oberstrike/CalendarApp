package main.com.calendarapp.views.activeness

import io.reactivex.subjects.BehaviorSubject
import main.com.calendarapp.views.AbstractViewModel

class ActivenessViewModel: AbstractViewModel(){

    val subject: BehaviorSubject<String> = BehaviorSubject.create()

    override fun onCleared() {
        super.onCleared()
    }

    fun setName(name: String){
        subject.onNext(name)
    }
}