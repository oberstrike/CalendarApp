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
                    @Convert(converter = WorkoutSetListConverter::class, dbType = String::class) val workoutSets: MutableList<WorkoutSet> = ArrayList()){


}

data class ExerciseDAO(val id: Long, val name: String, val workoutSets: MutableList<WorkoutSet>)


class WorkoutSetListConverter : PropertyConverter<MutableList<WorkoutSet>, String> {

    override fun convertToDatabaseValue(entityProperty: MutableList<WorkoutSet>?): String {
        return GsonBuilder().create().toJson(entityProperty)
    }

    override fun convertToEntityProperty(databaseValue: String?): MutableList<WorkoutSet> {
        val type = object : TypeToken<List<WorkoutSet>>() {}.type
        return GsonBuilder().create().fromJson(databaseValue, type)
    }

}