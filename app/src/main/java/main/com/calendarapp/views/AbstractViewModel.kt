package main.com.calendarapp.views

import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import io.objectbox.reactive.DataSubscription
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import main.com.calendarapp.util.rx.SchedulerProvider
import org.reactivestreams.Subscription

abstract class AbstractViewModel : ViewModel(){
    var subscription: DataSubscription? = null


    @CallSuper
    override fun onCleared() {
        super.onCleared()
        if(subscription != null)
            subscription?.cancel()

    }

}