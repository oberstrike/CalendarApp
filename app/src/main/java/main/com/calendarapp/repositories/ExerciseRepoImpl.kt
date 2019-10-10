package main.com.calendarapp.repositories

import io.objectbox.Box
import io.objectbox.rx.RxQuery
import io.reactivex.Observable
import main.com.calendarapp.data.ObjectBox
import main.com.calendarapp.models.Exercise
import main.com.calendarapp.models.Exercise_

class ExerciseRepoImpl : ExerciseRepo {

    private var exerciseBox: Box<Exercise> = ObjectBox.boxStore.boxFor(
        Exercise::class.java)

    override fun getAllExercises(): Observable<List<Exercise>> {
        val query = exerciseBox.query().build()
        return RxQuery.observable(query)

    }

    override fun getExerciseById(id: Long): Observable<List<Exercise>> =
        RxQuery.observable(exerciseBox.query().equal(Exercise_.id, id).build())

    override fun saveExercise(exercise: Exercise) {
        exerciseBox.put(exercise)
   }

}