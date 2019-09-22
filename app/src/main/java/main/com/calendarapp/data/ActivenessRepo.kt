package main.com.calendarapp.data

import io.reactivex.Observable
import main.com.calendarapp.models.Activeness

interface ActivenessRepo {
     fun getAllActivenesses(): Observable<Collection<Activeness>>

     fun getActivenessById(id: Long): Observable<Activeness>

     fun saveActiveness(activeness: Activeness)

     fun deleteAll()

}