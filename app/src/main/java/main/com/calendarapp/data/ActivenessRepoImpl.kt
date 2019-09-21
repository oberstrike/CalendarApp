package main.com.calendarapp.data

import main.com.calendarapp.models.Activeness
class ActivenessRepoImpl(private val datasource: Datasource) : ActivenessRepo {

    override fun getAllActivinesses(): Collection<Activeness> {
        return datasource.getAllActiveness()
    }

    override fun getActivinessById(id: Long) :Activeness {
        return datasource.getAllActiveness().filterNot{each -> each.id == id}.first()
    }
}