package main.com.calendarapp.models

import io.objectbox.annotation.Convert
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.converter.PropertyConverter
import main.com.calendarapp.models.interfaces.EnduranceWorkoutSet
import main.com.calendarapp.models.interfaces.SelfWeightWorkoutSet
import main.com.calendarapp.models.interfaces.StrengthWorkoutSet
import main.com.calendarapp.models.interfaces.SwimWorkoutSet


@Entity
data class WorkoutSet(
    @Id var id: Long,
    override var repetitions: Long,
    override var weight: Long,
    override var distance: Long = 0,
    override var time: Long = 0,
    override var lanes: Long = 0,
    @Convert(
        converter = MyWorkoutSetTypeConverter::class,
        dbType = Int::class
    )
    var type: WorkoutSetType = WorkoutSetType.STRENGTHWORKOUTSET
) : StrengthWorkoutSet, EnduranceWorkoutSet, SwimWorkoutSet, SelfWeightWorkoutSet


class MyWorkoutSetTypeConverter : PropertyConverter<WorkoutSetType, Int> {
    override fun convertToDatabaseValue(entityProperty: WorkoutSetType?): Int {
        return entityProperty?.id!!

    }

    override fun convertToEntityProperty(databaseValue: Int?): WorkoutSetType {
        for (value in WorkoutSetType.values()) {
            if (value.id == databaseValue)
                return value
        }
        return WorkoutSetType.STRENGTHWORKOUTSET

    }
}