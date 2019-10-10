package main.com.calendarapp.views.activeness

import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import main.com.calendarapp.models.Activeness
import main.com.calendarapp.models.Exercise
import main.com.calendarapp.models.WorkoutSet
import main.com.calendarapp.repositories.ActivenessRepo
import main.com.calendarapp.repositories.ExerciseRepo
import main.com.calendarapp.util.ExerciseContext
import main.com.calendarapp.util.MainContext
import main.com.calendarapp.util.rx.SchedulerProvider
import main.com.calendarapp.views.AbstractViewModel

class ActivenessViewModel(
    val provider: SchedulerProvider,
    private val activenessRepo: ActivenessRepo,
    private val exerciseRepo: ExerciseRepo
) : AbstractViewModel() {

    var startJob: Disposable? = null

    fun init(id: Long) {
        MainContext.activeActivenessObservable = activenessRepo.getActivenessById(id)
    }

    fun addExercise(count: Int): Int {

        val exercise = Exercise(0, "Übung")
        exercise.workoutSets = Array(count) {
            WorkoutSet(
                0,
                0,
                0
            )
        }.toMutableList()

        val activeness = MainContext.activeActivenessObservable

        launch {
            activeness.subscribe {
                val first = it.first()
                first.exercises.add(exercise)
                activenessRepo.saveActiveness(first)
            }
        }
        return 1
    }

    fun getActiveActiveness(): Observable<List<Activeness>> {
        return MainContext.activeActivenessObservable
    }

    fun setActiveExercise(id: Long) {
        ExerciseContext.activeExerciseObservable = exerciseRepo.getExerciseById(id)
    }

    fun addNewActiveExercise() {
        val exercise = Exercise(0, "Übung")
        exerciseRepo.saveExercise(exercise)
        ExerciseContext.activeExerciseObservable = exerciseRepo.getExerciseById(exercise.id)
    }

    fun onPause() {
        startJob?.dispose()
    }


}