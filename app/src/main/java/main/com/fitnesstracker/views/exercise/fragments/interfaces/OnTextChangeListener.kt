package main.com.fitnesstracker.views.exercise.fragments.interfaces

import main.com.fitnesstracker.models.WorkoutSet


interface OnTextChangeListener {
    fun onChange(workoutSet: WorkoutSet)
}

interface Fillable {
    fun setItems(list: ArrayList<WorkoutSet>)
}