package main.com.calendarapp.views.main


import io.reactivex.subjects.BehaviorSubject
import main.com.calendarapp.data.ActivenessRepo
import main.com.calendarapp.data.local.FileManager
import main.com.calendarapp.util.rx.SchedulerProvider
import main.com.calendarapp.views.AbstractViewModel

class MainViewModel(private val repository: ActivenessRepo,
                    val provider: SchedulerProvider,
                    val fileManager: FileManager
                   ) : AbstractViewModel(){

    val fileName = "main.json"
    val activities: BehaviorSubject<List<String>> = BehaviorSubject.create()

    fun addActiveness(){
        val value = activities.value
        var arrayList: ArrayList<String> = ArrayList()

        if(value != null){
            arrayList = ArrayList(value)
            arrayList.add("Training " +  (arrayList.size + 1))
            activities.onNext(arrayList)
        }
        else{
            arrayList.add("Training " + 1)
            activities.onNext(arrayList)
        }

    }
}