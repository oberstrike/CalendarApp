package main.com.calendarapp.views.day

import android.view.View
import androidx.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import main.com.calendarapp.views.AbstractViewModel

class DayViewModel: AbstractViewModel(){

    val subject: BehaviorSubject<String> = BehaviorSubject.create()

    override fun onCleared() {
        super.onCleared()
    }

    fun setName(name: String){
        subject.onNext(name)
    }
}