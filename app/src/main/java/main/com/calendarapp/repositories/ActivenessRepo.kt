package main.com.calendarapp.repositories

import io.reactivex.Observable
import main.com.calendarapp.models.Activeness

interface ActivenessRepo {
     fun getAllActivenesses(): Observable<List<Activeness>>

     fun getActivenessById(id: Long):  Observable<List<Activeness>>

     fun saveActiveness(activeness: Activeness)

     fun deleteAll()

     fun delete(activeness: Activeness)

     fun getAllActivenessesByYear(year: Int): Observable<List<Activeness>>

    fun getAllActivenessSize(): Int
}