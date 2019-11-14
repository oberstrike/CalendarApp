package main.com.fitnesstracker

import io.objectbox.rx.RxQuery
import main.com.fitnesstracker.boxTests.AbstractObjectBoxTest
import main.com.fitnesstracker.models.Activeness
import org.joda.time.DateTime
import org.junit.Test

class ActivenessTest : AbstractObjectBoxTest() {



    @Test
    fun test() {

        val activeness = Activeness(0, DateTime.now())

        val activenessBox = super.store?.boxFor(Activeness::class.java)

        val year = 2019

        if (activenessBox != null) {
            activenessBox.put(activeness)
            val query = RxQuery
                .observable(activenessBox.query().filter { it.date.year == year }.build())

        }

    }

}