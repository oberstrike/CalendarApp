package main.com.calendarapp.views.activeness

import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import main.com.calendarapp.models.Activeness
import main.com.calendarapp.models.ActivenessType
import main.com.calendarapp.models.Exercise
import main.com.calendarapp.models.ExerciseType
import main.com.calendarapp.repositories.ActivenessRepo
import main.com.calendarapp.repositories.ExerciseRepo
import main.com.calendarapp.repositories.WorkoutSetRepo
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

    fun setActiveExercise(id: Long) {
        ExerciseContext.activeExerciseObservable = exerciseRepo.getExerciseById(id)
    }

    fun addNewExercise() {
        val exercise = Exercise(0, "Übung")

        val activenessType = getActivActivenessType()

        exercise.type = when (activenessType) {
            ActivenessType.STRENGTH -> ExerciseType.STRENGTHWORKOUTSET
            ActivenessType.SWIMMING -> ExerciseType.SWIMWORKOUTSET
            ActivenessType.ENDURANCE -> ExerciseType.ENDURANCEWORKOUTSET
        }

        exerciseRepo.saveExercise(exercise)
        ExerciseContext.activeExerciseObservable = exerciseRepo.getExerciseById(exercise.id)
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
    }


}