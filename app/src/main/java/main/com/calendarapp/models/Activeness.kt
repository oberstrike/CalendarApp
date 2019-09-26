package main.com.calendarapp.models

import com.fatboyindustrial.gsonjodatime.DateTimeConverter
import io.objectbox.annotation.Backlink
import io.objectbox.annotation.Convert
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.converter.PropertyConverter
import io.objectbox.relation.ToMany
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat


@Entity
data class Activeness constructor(@Id(assignable = false) var id: Long = 0,
                                  @Convert(converter = MyDateTimeConverter::class, dbType = String::class) var date:DateTime
                                  ){
    lateinit var exercises: MutableList<Exercise>
}


class MyDateTimeConverter: PropertyConverter<DateTime, String>{
    override fun convertToDatabaseValue(entityProperty: DateTime?): String =
        entityProperty?.toString( DateTimeFormat.forPattern("dd.MM.yyyy HH:mm"))!!



    override fun convertToEntityProperty(databaseValue: String?): DateTime
            = DateTime.parse(databaseValue, DateTimeFormat.forPattern("dd.MM.yyyy HH:mm"))

}

