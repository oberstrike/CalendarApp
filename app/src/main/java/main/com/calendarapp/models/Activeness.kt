package main.com.calendarapp.models

import io.objectbox.annotation.Convert
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.converter.PropertyConverter
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat


@Entity
data class Activeness(
    @Id(assignable = false) var id: Long = 0,
    @Convert(
                                      converter = MyDateTimeConverter::class,
                                      dbType = String::class
                                  ) var date: DateTime,
    var name: String = "",
    @Convert(
                                      converter = MyActivenessTypeConverter::class,
                                      dbType = String::class
                                  )
                                  var type: ActivenessType = ActivenessType.STRENGTH
                                  ){
    lateinit var exercises: MutableList<Exercise>


}


class MyDateTimeConverter: PropertyConverter<DateTime, String>{
    override fun convertToDatabaseValue(entityProperty: DateTime?): String =
        entityProperty?.toString( DateTimeFormat.forPattern("dd.MM.yyyy HH:mm"))!!



    override fun convertToEntityProperty(databaseValue: String?): DateTime
            = DateTime.parse(databaseValue, DateTimeFormat.forPattern("dd.MM.yyyy HH:mm"))
}

class MyActivenessTypeConverter : PropertyConverter<ActivenessType, String> {
    override fun convertToDatabaseValue(entityProperty: ActivenessType?): String =
        entityProperty?.text!!

    override fun convertToEntityProperty(databaseValue: String?): ActivenessType {
        for (type in ActivenessType.values()) {
            if (type.text == databaseValue)
                return type
        }
        return ActivenessType.STRENGTH
    }
}

