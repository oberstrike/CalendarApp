package main.com.calendarapp.views.main

import android.view.View
import androidx.lifecycle.ViewModel
import com.google.android.material.snackbar.Snackbar
import main.com.calendarapp.data.AppointmentRepository
import main.com.calendarapp.models.Appointment
import main.com.calendarapp.views.AbstractViewModel

class MainViewModel(val repository: AppointmentRepository) : AbstractViewModel(){

    fun onLoad(view: View):Unit {
        Snackbar.make(view, "Hello from the ViewModel", Snackbar.LENGTH_LONG)
            .setAction("Action", null).show()
    }

    fun getAppointments():Collection<Appointment>{
        return repository.getContent()
    }

    override fun onCleared() {
        super.onCleared()
    }
}