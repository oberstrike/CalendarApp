package main.com.calendarapp.util.rx


import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SchedulerProviderImpl : SchedulerProvider {

    override fun computation() = Schedulers.computation()

    override fun io() = Schedulers.io();

    override fun ui() = AndroidSchedulers.mainThread()
}