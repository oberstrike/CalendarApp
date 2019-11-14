package main.com.fitnesstracker.repositories

import io.objectbox.Box
import io.objectbox.rx.RxQuery
import io.reactivex.Observable
import main.com.fitnesstracker.data.ObjectBox
import main.com.fitnesstracker.models.Exercise
import main.com.fitnesstracker.models.Exercise_

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

    override fun deleteExercise(exercise: Exercise) {
        exerciseBox.remove(exercise)
    }
}