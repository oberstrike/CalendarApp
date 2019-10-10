package main.com.calendarapp.models

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class Exercise(@Id var id:Long = 0,
                    val name: String){
        lateinit var workoutSets: MutableList<WorkoutSet>
}