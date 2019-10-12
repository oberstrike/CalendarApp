package main.com.calendarapp.views.exercise.fragments

import main.com.calendarapp.models.WorkoutSet


interface OnTextChangeListener {
    fun onChange(workoutSet: WorkoutSet)
}

interface Fillable {
    var items: ArrayList<WorkoutSet>
    fun notifyDataSetChanged(): Unit
}