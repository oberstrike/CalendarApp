package main.com.calendarapp.models

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.annotation.Uid
import main.com.calendarapp.models.interfaces.IEnduranceWorkoutSet
import main.com.calendarapp.models.interfaces.IStrengthWorkoutSet
import main.com.calendarapp.models.interfaces.ISwimWorkoutSet
import main.com.calendarapp.models.interfaces.ITrainWithoutWeightWorkoutSet


@Entity
data class WorkoutSet(
    @Id var id: Long,
    override var repetitions: Long,
    override var weight: Long,
    override var distance: Long = 0,
    @Uid(4970102307707177260L)
    override var time: Float = 0.0f,
    override var lanes: Long = 0
) : IStrengthWorkoutSet, IEnduranceWorkoutSet, ISwimWorkoutSet, ITrainWithoutWeightWorkoutSet


