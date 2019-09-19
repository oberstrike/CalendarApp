package main.com.calendarapp.di

import main.com.calendarapp.data.AppointmentRepository
import main.com.calendarapp.data.AppointmentRepositoryImpl
import main.com.calendarapp.util.rx.SchedulerProvider
import main.com.calendarapp.util.rx.SchedulerProviderImpl
import main.com.calendarapp.views.activeness.ActivenessViewModel
import main.com.calendarapp.views.main.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module


val calender_module = module {

    single { AppointmentRepositoryImpl() } bind AppointmentRepository::class

    viewModel { MainViewModel(get()) }

    viewModel { ActivenessViewModel() }
}

val rxModule = module {

    single { SchedulerProviderImpl() } bind SchedulerProvider::class
}

var appModules = listOf(calender_module, rxModule)