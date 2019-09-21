package main.com.calendarapp.data.local

import com.google.gson.Gson
import main.com.calendarapp.data.Datasource
import main.com.calendarapp.models.Activeness


class LocalDatasource(val fileManager: FileManager) : Datasource {

    var activeness = ArrayList<Activeness>()

    init {
        activeness = Gson().fromJson(fileManager.getContent(), ArrayList<Activeness>().javaClass)
    }

    override fun getAllActiveness(): ArrayList<Activeness> {
        return activeness
    }

}