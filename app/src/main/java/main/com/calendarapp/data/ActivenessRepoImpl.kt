package main.com.calendarapp.data

import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import main.com.calendarapp.models.Activeness
class ActivenessRepoImpl(private val datasource: Datasource) : ActivenessRepo {

    val subject: BehaviorSubject<Collection<Activeness>> = BehaviorSubject.create()

    override fun getAllActivenesses(): Observable<Collection<Activeness>>  {
        subject.onNext(datasource.getAllActiveness())
        return subject
    }

    override fun getActivenessById(id: Long) : Observable<Activeness> {
        return Observable.just( datasource.getAllActiveness().filterNot{ each -> each.id == id}.first() )
    }

    override fun saveActiveness(activeness: Activeness) {
        datasource.addActiveness(activeness)
        subject.onNext(datasource.getAllActiveness())
    }

    override fun deleteAll() {
        datasource.deleteAll()
    }
}