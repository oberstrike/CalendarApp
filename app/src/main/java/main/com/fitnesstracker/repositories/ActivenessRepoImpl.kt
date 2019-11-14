package main.com.fitnesstracker.repositories

import io.objectbox.Box
import io.objectbox.rx.RxQuery
import io.reactivex.Observable
import main.com.fitnesstracker.data.ObjectBox
import main.com.fitnesstracker.models.Activeness
import main.com.fitnesstracker.models.Activeness_

class ActivenessRepoImpl : ActivenessRepo {


    private var activenessBox: Box<Activeness> = ObjectBox.boxStore.boxFor(Activeness::class.java)

    override fun getAllActivenesses() = RxQuery.observable(activenessBox.query().build())!!

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

    override fun getAllActivenessSize(): Int {
        return activenessBox.query().build().find().size
    }
}