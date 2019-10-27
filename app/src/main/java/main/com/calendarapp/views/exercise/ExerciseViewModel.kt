package main.com.calendarapp.views.exercise

import main.com.calendarapp.models.ExerciseType
import main.com.calendarapp.models.WorkoutSet
import main.com.calendarapp.repositories.ActivenessRepo
import main.com.calendarapp.repositories.ExerciseRepo
import main.com.calendarapp.repositories.WorkoutSetRepo
import main.com.calendarapp.util.ActivenessContext
import main.com.calendarapp.util.MainContext
import main.com.calendarapp.util.rx.SchedulerProvider
import main.com.calendarapp.views.AbstractViewModel

class ExerciseViewModel(
    private val exerciseRepo: ExerciseRepo,
    private val workoutSetRepo: WorkoutSetRepo,
    private val activenessRepo: ActivenessRepo,
    val schedulerProvider: SchedulerProvider) : AbstractViewModel() {

    var exerciseId: Long = 0
    var type: ExerciseType = ExerciseType.STRENGTHWORKOUTSET

    fun init() {
        launch {
            ActivenessContext.activeExerciseObservable.subscribe {
                val exercise = it.first()
                type = exercise.type
                exerciseId = exerciseId

            }
        }
    }

    fun addWorkoutSet() {
        val exercise = ActivenessContext.activeExercise
        val workoutSet = MainContext.createWorkoutSet()
        saveWorkoutSet(workoutSet)
        exercise.workoutSets.add(workoutSet)
    }


    fun saveWorkoutSet(workoutSet: WorkoutSet) {
        workoutSetRepo.saveWorkoutSet(workoutSet)

        val exercise =
            ActivenessContext.activeExerciseObservable.first(ArrayList()).toFuture().get()
                .first()
        val activeness =
            MainContext.activeActivenessObservable.first(ArrayList()).toFuture().get()
                .first()

        if (!exercise.workoutSets.contains(workoutSet)) {
            exercise.workoutSets.add(workoutSet)
            exerciseRepo.saveExercise(exercise)
        }

        if (!activeness.exercises.contains(exercise)) {
            activeness.exercises.add(exercise)
            activenessRepo.saveActiveness(activeness)
        }

    }

    fun setExerciseType(exerciseType: ExerciseType) {
        ActivenessContext.activeExercise.type = exerciseType
        exerciseRepo.saveExercise(ActivenessContext.activeExercise)
    }

}