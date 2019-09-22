package main.com.calendarapp.data.local

import com.fatboyindustrial.gsonjodatime.Converters
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import main.com.calendarapp.data.Datasource
import main.com.calendarapp.models.Activeness
import org.joda.time.convert.Converter
import java.lang.Exception


class LocalDatasource(val fileManager: FileManager) : Datasource {

    lateinit var activenesses: ArrayList<Activeness>
    private val gson = Converters.registerDateTime(GsonBuilder()).create()

    init {
        init()
    }

    override fun getAllActiveness(): ArrayList<Activeness> {
        return activenesses
    }

    override fun addActiveness(activeness: Activeness) {
        activenesses.add(activeness)
        fileManager.appendContent(gson.toJson(activenesses, ArrayList<Activeness>().javaClass))
    }

    override fun deleteAll() {
        fileManager.reset()
        init()
    }

    private fun init(){

        try {
            activenesses = gson.fromJson(fileManager.getContent(), ArrayList<Activeness>().javaClass)
        }catch (ex: Exception){
            activenesses = ArrayList()
        }
    }

}