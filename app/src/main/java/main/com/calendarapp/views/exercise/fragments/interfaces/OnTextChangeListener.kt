package main.com.calendarapp.views.exercise.fragments.interfaces

import main.com.calendarapp.models.WorkoutSet


interface OnTextChangeListener {
    fun onChange(workoutSet: WorkoutSet)
}

interface Fillable {
    fun setItems(list: ArrayList<WorkoutSet>)
}