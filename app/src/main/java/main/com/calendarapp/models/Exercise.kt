package main.com.calendarapp.models

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import io.objectbox.annotation.Backlink
import io.objectbox.annotation.Convert
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.converter.PropertyConverter
import io.objectbox.relation.ToMany
import io.objectbox.relation.ToOne
import org.joda.time.DateTime

@Entity
data class Exercise(@Id var id:Long = 0,
                    val name: String,
                    @Convert(converter = WorkoutSetArrayConverter::class, dbType = String::class) var workoutSets: Array<WorkoutSet>){

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Exercise

        if (id != other.id) return false
        if (name != other.name) return false
        if (!workoutSets.contentEquals(other.workoutSets)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + workoutSets.contentHashCode()
        return result
    }
}

data class ExerciseDAO(val id: Long, val name: String, val workoutSets: MutableList<WorkoutSet>)


class WorkoutSetArrayConverter : PropertyConverter<Array<WorkoutSet>, String> {

    override fun convertToDatabaseValue(entityProperty: Array<WorkoutSet>?): String {
        return GsonBuilder().create().toJson(entityProperty)
    }

    override fun convertToEntityProperty(databaseValue: String?): Array<WorkoutSet> {
        val type = object : TypeToken<Array<WorkoutSet>>() {}.type
        return GsonBuilder().create().fromJson(databaseValue, type)
    }

}