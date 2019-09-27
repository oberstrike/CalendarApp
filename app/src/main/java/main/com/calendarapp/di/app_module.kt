package main.com.calendarapp.di

import main.com.calendarapp.repositories.ActivenessRepo
import main.com.calendarapp.repositories.ActivenessRepoImpl
import main.com.calendarapp.util.rx.SchedulerProvider
import main.com.calendarapp.util.rx.SchedulerProviderImpl
import main.com.calendarapp.views.activeness.ActivenessViewModel
import main.com.calendarapp.views.exercise.ExerciseViewModel
import main.com.calendarapp.views.main.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module


val calender_module = module {

    single { ActivenessRepoImpl(get())} bind ActivenessRepo::class

    viewModel { MainViewModel(get(), get()) }

    viewModel { ActivenessViewModel(get(), get()) }

    viewModel {ExerciseViewModel(get(), get())}

    single { SchedulerProviderImpl() } bind SchedulerProvider::class


}


var appModules = listOf(calender_module)