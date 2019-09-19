package main.com.calendarapp.di

import main.com.calendarapp.data.ExerciseRepository
import main.com.calendarapp.data.ExerciseRepositoryImpl
import main.com.calendarapp.util.rx.SchedulerProvider
import main.com.calendarapp.util.rx.SchedulerProviderImpl
import main.com.calendarapp.views.activeness.ActivenessViewModel
import main.com.calendarapp.views.main.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module


val calender_module = module {

    single { ExerciseRepositoryImpl() } bind ExerciseRepository::class

    viewModel { MainViewModel(get(), get()) }

    viewModel { ActivenessViewModel(get()) }

    single { SchedulerProviderImpl() } bind SchedulerProvider::class
}

val rxModule = module {

}

var appModules = listOf(calender_module, rxModule)