package main.com.calendarapp.models

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import main.com.calendarapp.models.interfaces.EnduranceWorkoutSet
import main.com.calendarapp.models.interfaces.StrengthWorkoutSet
import main.com.calendarapp.models.interfaces.SwimWorkoutSet
import main.com.calendarapp.models.interfaces.TrainWithoutWeightWorkoutSet


@Entity
data class WorkoutSet(
    @Id var id: Long,
    override var repetitions: Long,
    override var weight: Long,
    override var distance: Long = 0,
    override var time: Long = 0,
    override var lanes: Long = 0
) : StrengthWorkoutSet, EnduranceWorkoutSet, SwimWorkoutSet, TrainWithoutWeightWorkoutSet


