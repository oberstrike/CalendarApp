package main.com.fitnesstracker.views.activeness

import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import main.com.fitnesstracker.models.*
import main.com.fitnesstracker.repositories.ActivenessRepo
import main.com.fitnesstracker.repositories.ExerciseRepo
import main.com.fitnesstracker.repositories.WorkoutSetRepo
import main.com.fitnesstracker.util.ActivenessContext
import main.com.fitnesstracker.util.ExerciseContext
import main.com.fitnesstracker.util.MainContext
import main.com.fitnesstracker.util.rx.SchedulerProvider
import main.com.fitnesstracker.views.AbstractViewModel

class ActivenessViewModel(
    val provider: SchedulerProvider,
    private val activenessRepo: ActivenessRepo,
    private val workoutSetRepo: WorkoutSetRepo,
    private val exerciseRepo: ExerciseRepo
) : AbstractViewModel() {

    var startJob: Disposable? = null

    fun getActiveActiveness(): Observable<List<Activeness>> {
        return MainContext.activeActivenessObservable
    }

    fun setActiveExercise(exercise: Exercise) {
        ActivenessContext.activeExercise = exercise
        ActivenessContext.activeExerciseObservable = exerciseRepo.getExerciseById(exercise.id)
        ExerciseContext.workoutSets = workoutSetRepo.getAllWorkoutSetsByExercise(exercise)
    }

    fun addNewExercise(count: Int) {
        val exercise = MainContext.createExercise(ExerciseType.STRENGTHWORKOUTSET)

        val activenessType = getActivActivenessType()

        exercise.type = when (activenessType) {
            ActivenessType.STRENGTH -> ExerciseType.STRENGTHWORKOUTSET
            ActivenessType.SWIMMING -> ExerciseType.SWIMWORKOUTSET
            ActivenessType.ENDURANCE -> ExerciseType.ENDURANCEWORKOUTSET
        }

        for (x in 1..count) {
            val workoutSet = WorkoutSet(0, 0, 0f)
            workoutSetRepo.saveWorkoutSet(workoutSet)
            exercise.workoutSets.add(workoutSet)
        }

        exerciseRepo.saveExercise(exercise)

        ExerciseContext.workoutSets = workoutSetRepo.getAllWorkoutSetsByExercise(exercise)
        ActivenessContext.activeExercise = exercise
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