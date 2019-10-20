package main.com.calendarapp.repositories

import io.objectbox.Box
import io.objectbox.rx.RxQuery
import io.reactivex.Observable
import main.com.calendarapp.data.ObjectBox
import main.com.calendarapp.ext.Weekday
import main.com.calendarapp.models.Activeness
import main.com.calendarapp.models.Activeness_
import org.joda.time.DateTime

class ActivenessRepoImpl : ActivenessRepo {


    private var activenessBox: Box<Activeness> = ObjectBox.boxStore.boxFor(Activeness::class.java)

    override fun getAllActivenesses() = RxQuery.observable( activenessBox.query().build())!!

    fun getAllActivenessByDate(startDate: DateTime, endDate: DateTime) =
        RxQuery.observable(activenessBox.query().filter {
            it.date.isAfter(startDate) && it.date.isBefore(endDate)
        }.build())

    fun getAllActinvessByWeekDay(weekday: Weekday) =
        RxQuery.observable(activenessBox.query().filter {
            weekday.id == it.date.dayOfWeek
        }.build())


    override fun getActivenessById(id: Long) = RxQuery.observable( activenessBox.query().equal(Activeness_.id, id).build())!!

    override fun saveActiveness(activeness: Activeness) {
        activenessBox.put(activeness)
    }

    override fun deleteAll() {
        activenessBox.removeAll()
    }

    override fun delete(activeness: Activeness) {
        activenessBox.remove(activeness)
    }


    override fun getAllActivenessesByYear(year: Int): Observable<List<Activeness>> {
        return RxQuery.observable(activenessBox.query().filter { it.date.year == year }.build())
    }
}