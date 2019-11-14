package main.com.fitnesstracker.repositories

import io.objectbox.Box
import io.objectbox.rx.RxQuery
import io.reactivex.Observable
import main.com.fitnesstracker.data.ObjectBox
import main.com.fitnesstracker.models.Exercise
import main.com.fitnesstracker.models.WorkoutSet

class WorkoutSetRepoImpl : WorkoutSetRepo{

    private var exerciseBox: Box<WorkoutSet> = ObjectBox.boxStore.boxFor(
        WorkoutSet::class.java)

    override fun saveWorkoutSet(workoutSet: WorkoutSet) {
        exerciseBox.put(workoutSet)
    }

    override fun getAllWorkoutSets(): Observable<List<WorkoutSet>> {
        return RxQuery.observable(exerciseBox.query().build())
    }

    override fun delete(x: WorkoutSet) {
        exerciseBox.remove(x)
    }

    override fun getAllWorkoutSetsByExercise(exercise: Exercise): Observable<List<WorkoutSet>> {
        return RxQuery.observable(exerciseBox.query().filter {
            exercise.workoutSets.map { each -> each.id }.contains(it.id)
        }.build())
    }
}