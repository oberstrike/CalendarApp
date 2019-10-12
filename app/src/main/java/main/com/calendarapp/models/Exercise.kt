package main.com.calendarapp.models

import io.objectbox.annotation.Convert
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.converter.PropertyConverter

@Entity
data class Exercise(@Id var id:Long = 0,
                    val name: String,
                    @Convert(
                        converter = MyExerciseTypeConverter::class,
                        dbType = Int::class
                    )
                    var type: ExerciseType = ExerciseType.STRENGTHWORKOUTSET
) {

        lateinit var workoutSets: MutableList<WorkoutSet>
}


class MyExerciseTypeConverter : PropertyConverter<ExerciseType, Int> {
    override fun convertToDatabaseValue(entityProperty: ExerciseType?): Int {
        return entityProperty?.id!!

    }

    override fun convertToEntityProperty(databaseValue: Int?): ExerciseType {
        for (value in ExerciseType.values()) {
            if (value.id == databaseValue)
                return value
        }
        return ExerciseType.STRENGTHWORKOUTSET

    }
}

enum class ExerciseType(val id: Int) {
    STRENGTHWORKOUTSET(0),
    ENDURANCEWORKOUTSET(1),
    SWIMWORKOUTSET(2),
    SELFWEIGHTWORKOUTSET(3)

}