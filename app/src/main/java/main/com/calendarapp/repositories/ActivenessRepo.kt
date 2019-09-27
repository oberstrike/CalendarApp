package main.com.calendarapp.repositories

import io.objectbox.query.Query
import main.com.calendarapp.models.Activeness

interface ActivenessRepo {
     fun getAllActivenesses(): Query<Activeness>

     fun getActivenessById(id: Long): Query<Activeness>

     fun saveActiveness(activeness: Activeness)

     fun deleteAll()

}