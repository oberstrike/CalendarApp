package main.com.calendarapp.di

import main.com.calendarapp.data.ObjectBox
import main.com.calendarapp.repositories.*
import main.com.calendarapp.util.rx.SchedulerProvider
import main.com.calendarapp.util.rx.SchedulerProviderImpl
import main.com.calendarapp.views.activeness.ActivenessViewModel
import main.com.calendarapp.views.exercise.ExerciseViewModel
import main.com.calendarapp.views.main.MainViewModel
import main.com.calendarapp.views.statistic.StatisticsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

val fitnessModule = module {

    single { ActivenessRepoImpl() } bind ActivenessRepo::class

    single { ExerciseRepoImpl() } bind ExerciseRepo::class

    single { WorkoutSetRepoImpl() } bind WorkoutSetRepo::class

    viewModel { MainViewModel(get(), get(), get(), get()) }

    viewModel { ActivenessViewModel(get(), get(), get(), get()) }

    viewModel {ExerciseViewModel(get(), get(), get(), get())}

    viewModel { StatisticsViewModel(get(), get(), get()) }

    single { SchedulerProviderImpl() } bind SchedulerProvider::class

    single { ObjectBox.init(get ()) }

}


var appModules = listOf(fitnessModule)