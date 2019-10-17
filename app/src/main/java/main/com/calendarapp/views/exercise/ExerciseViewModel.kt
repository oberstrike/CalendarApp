package main.com.calendarapp.views.exercise

import android.util.Log
import main.com.calendarapp.models.ExerciseType
import main.com.calendarapp.models.WorkoutSet
import main.com.calendarapp.repositories.ActivenessRepo
import main.com.calendarapp.repositories.ExerciseRepo
import main.com.calendarapp.repositories.WorkoutSetRepo
import main.com.calendarapp.util.ExerciseContext
import main.com.calendarapp.util.MainContext
import main.com.calendarapp.util.rx.SchedulerProvider
import main.com.calendarapp.views.AbstractViewModel

class ExerciseViewModel(
    private val exerciseRepo: ExerciseRepo,
    private val workoutSetRepo: WorkoutSetRepo,
    private val activenessRepo: ActivenessRepo,
    val schedulerProvider: SchedulerProvider) : AbstractViewModel() {

    var exerciseId: Long = 0
    lateinit var type: ExerciseType

    fun init(setCount: Int) {

        val future = ExerciseContext.activeExerciseObservable.first(ArrayList()).toFuture()
        val it = future.get()

        val exercise = it.first()
        type = exercise.type
        exerciseId = exercise.id
        if (exercise.workoutSets.size == 0) {
            for (i in 0 until setCount) {
                val workoutSet = WorkoutSet(0, 0, 0)
                workoutSetRepo.saveWorkoutSet(workoutSet)
                exercise.workoutSets.add(workoutSet)
            }
            exerciseRepo.saveExercise(exercise)
        }
        Log.i("Info", "Anzahl der Workouts ${exercise.workoutSets.size}")
    }

    fun save() {

    }

    fun saveWorkoutSet(workoutSet: WorkoutSet) {
        workoutSetRepo.saveWorkoutSet(workoutSet)

        val exercise =
            ExerciseContext.activeExerciseObservable.first(ArrayList()).toFuture().get()
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

}