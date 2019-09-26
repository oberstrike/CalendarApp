package main.com.calendarapp

import com.fatboyindustrial.gsonjodatime.Converters
import com.google.gson.GsonBuilder
import io.objectbox.Box
import main.com.calendarapp.boxTests.AbstractObjectBoxTest
import main.com.calendarapp.models.Activeness
import main.com.calendarapp.models.Exercise
import main.com.calendarapp.models.WorkoutSet
import org.joda.time.DateTime
import org.junit.Assert
import org.junit.Test

class ActivenessTest : AbstractObjectBoxTest(){

    @Test
    fun serialisationTest(){
        val box = store?.boxFor(Activeness::class.java)
        val activeness = Activeness(0, DateTime.now())
        val exercise = Exercise(0,"Test")
        exercise.workoutSets.add(WorkoutSet(5, 10))


        activeness.exercises.add(exercise)
        val gson = Converters.registerDateTime(GsonBuilder())

        val content = gson.create().toJson(activeness, Activeness::class.java)

        println(content)

        box?.put(activeness)

        val all = box?.all!!
        val first = all.first()
        val newContent = gson.create().toJson(first)
        println(newContent)


        Assert.assertEquals(1, box?.all?.size)

    }

}