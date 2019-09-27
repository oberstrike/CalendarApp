package main.com.calendarapp

import com.fatboyindustrial.gsonjodatime.Converters
import com.google.gson.GsonBuilder
import io.objectbox.Box
import main.com.calendarapp.boxTests.AbstractObjectBoxTest
import main.com.calendarapp.ext.GsonObject
import main.com.calendarapp.models.Activeness
import main.com.calendarapp.models.Exercise
import main.com.calendarapp.models.WorkoutSet
import main.com.calendarapp.testdata.TestData
import org.joda.time.DateTime
import org.junit.Assert
import org.junit.Test

class ActivenessTest : AbstractObjectBoxTest(){

    @Test
    fun serialisationTest(){
        val box = store?.boxFor(Activeness::class.java)
        val list = TestData.TEST_1.activeness()
        val gson = GsonObject.gson

        for (activeness in list) {
            box?.put(activeness)
        }

        val all = box?.all!!
        val first = all.first()
        val newContent = gson.toJson(first)
        println(newContent)


        Assert.assertEquals(1, box?.all?.size)

    }

}