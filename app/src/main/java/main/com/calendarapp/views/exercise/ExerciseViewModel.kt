package main.com.calendarapp.views.exercise

import main.com.calendarapp.models.Exercise
import main.com.calendarapp.models.WorkoutSet
import main.com.calendarapp.repositories.ActivenessRepo
import main.com.calendarapp.repositories.ExerciseRepo
import main.com.calendarapp.repositories.WorkoutSetRepo
import main.com.calendarapp.util.ExerciseContext
import main.com.calendarapp.util.MainContext
import main.com.calendarapp.util.rx.SchedulerProvider
import main.com.calendarapp.views.AbstractViewModel

class ExerciseViewModel(
    val exerciseRepo: ExerciseRepo,
    val workoutSetRepo: WorkoutSetRepo,
    val activenessRepo: ActivenessRepo,
    val schedulerProvider: SchedulerProvider) : AbstractViewModel() {
    var exerciseId: Long = 0

    fun init(setCount: Int) {
        launch {
            ExerciseContext.activeExerciseObservable.subscribe {
                val exercise = it.first()
                exerciseId = exercise.id
                if (exercise.workoutSets.size == 0) {
                    for (i in 0 until setCount) {
                        val workoutSet = WorkoutSet(0, 0, 0)
                        workoutSetRepo.saveWorkoutSet(workoutSet)
                        exercise.workoutSets.add(workoutSet)
                    }
                    exerciseRepo.saveExercise(exercise)
                }
            }

        }

    }

    fun saveExercise(exercise: Exercise) {
        exerciseRepo.saveExercise(exercise)

        launch {
            MainContext.activeActivenessObservable.subscribe{
                val first = it.first()
                if(first.exercises.contains(exercise))
                    return@subscribe

                first.exercises.add(exercise)
                activenessRepo.saveActiveness(first)
            }
        }
    }

    fun saveWorkoutSet(workoutSet: WorkoutSet) {
        workoutSetRepo.saveWorkoutSet(workoutSet)
    }

}