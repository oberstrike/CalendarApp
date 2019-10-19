package main.com.calendarapp.views.activeness

import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import main.com.calendarapp.models.*
import main.com.calendarapp.repositories.ActivenessRepo
import main.com.calendarapp.repositories.ExerciseRepo
import main.com.calendarapp.repositories.WorkoutSetRepo
import main.com.calendarapp.util.ActivenessContext
import main.com.calendarapp.util.ExerciseContext
import main.com.calendarapp.util.MainContext
import main.com.calendarapp.util.rx.SchedulerProvider
import main.com.calendarapp.views.AbstractViewModel

class ActivenessViewModel(
    val provider: SchedulerProvider,
    private val activenessRepo: ActivenessRepo,
    private val workoutSetRepo: WorkoutSetRepo,
    private val exerciseRepo: ExerciseRepo
) : AbstractViewModel() {

    var startJob: Disposable? = null
    var activenessId: Long = 0

    fun init(id: Long) {
        activenessId = id
        MainContext.activeActivenessObservable = activenessRepo.getActivenessById(id)

    }

    fun getActiveActiveness(): Observable<List<Activeness>> {
        return MainContext.activeActivenessObservable
    }

    fun setActiveExercise(exercise: Exercise) {
        ActivenessContext.activeExerciseObservable = exerciseRepo.getExerciseById(exercise.id)
        ExerciseContext.workoutSets = workoutSetRepo.getAllWorkoutSetsByExercise(exercise)
    }

    fun addNewExercise(count: Int) {
        val exercise = Exercise(0, "Übung")

        val activenessType = getActivActivenessType()

        exercise.type = when (activenessType) {
            ActivenessType.STRENGTH -> ExerciseType.STRENGTHWORKOUTSET
            ActivenessType.SWIMMING -> ExerciseType.SWIMWORKOUTSET
            ActivenessType.ENDURANCE -> ExerciseType.ENDURANCEWORKOUTSET
        }

        for (x in 1..count) {
            val workoutSet = WorkoutSet(0, 0, 0)
            workoutSetRepo.saveWorkoutSet(workoutSet)
            exercise.workoutSets.add(workoutSet)
        }

        exerciseRepo.saveExercise(exercise)

        ExerciseContext.workoutSets = workoutSetRepo.getAllWorkoutSetsByExercise(exercise)
        ActivenessContext.activeExerciseObservable = exerciseRepo.getExerciseById(exercise.id)
    }

    fun onPause() {
        startJob?.dispose()
    }

    private fun getActivActivenessType(): ActivenessType {
        return MainContext.activeActivenessObservable
            .first(ArrayList())
            .toFuture()
            .get()
            .first()
            .type
    }

    fun deleteExercise(exercise: Exercise): Boolean {
        for (x in exercise.workoutSets) {
            workoutSetRepo.delete(x)
        }
        exerciseRepo.deleteExercise(exercise)
        val activeness =
            MainContext.activeActivenessObservable.first(ArrayList()).toFuture().get().firstOrNull()
        if (activeness != null)
            activenessRepo.saveActiveness(activeness)
        return true
    }

    fun renameExercise(exercise: Exercise, name: String) {
        exercise.name = name
        exerciseRepo.saveExercise(exercise)
        val activeness =
            MainContext.activeActivenessObservable.first(ArrayList()).toFuture().get().firstOrNull()
        if (activeness != null)
            activenessRepo.saveActiveness(activeness)
    }


}