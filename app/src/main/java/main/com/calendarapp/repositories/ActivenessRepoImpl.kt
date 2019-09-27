package main.com.calendarapp.repositories

import android.content.Context
import io.objectbox.Box
import io.objectbox.query.Query
import main.com.calendarapp.data.ObjectBox
import main.com.calendarapp.models.Activeness
import main.com.calendarapp.models.Activeness_
import main.com.calendarapp.models.Exercise

class ActivenessRepoImpl(context: Context) : ActivenessRepo {


    var activenessBox: Box<Activeness>

    init {
        ObjectBox.init(context)
        activenessBox = ObjectBox.boxStore.boxFor(Activeness::class.java)
    }

    override fun getAllActivenesses(): Query<Activeness>  {
        return activenessBox.query().build()
    }

    override fun getActivenessById(id: Long) : Query<Activeness> {
        return activenessBox.query().equal(Activeness_.id, id).build()
    }

    override fun saveActiveness(activeness: Activeness) {
        activenessBox.put(activeness)
    }

    override fun deleteAll() {
        activenessBox.removeAll()
    }

    fun addExercise(id:Long,exercise: Exercise){
        val activeness = getActivenessById(id).findFirst()
        activeness?.exercises?.add(exercise)

        saveActiveness(activeness!!)
    }
}

