package main.com.calendarapp.views.exercise.fragments.interfaces

import main.com.calendarapp.models.WorkoutSet


interface OnTextChangeListener {
    fun onChange(workoutSet: WorkoutSet)
}

interface Fillable {
    var items: ArrayList<WorkoutSet>
    fun notifyDataSetChanged(): Unit
}