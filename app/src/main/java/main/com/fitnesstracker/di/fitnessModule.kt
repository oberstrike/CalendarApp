package main.com.fitnesstracker.di

import main.com.fitnesstracker.data.ObjectBox
import main.com.fitnesstracker.repositories.*
import main.com.fitnesstracker.util.rx.SchedulerProvider
import main.com.fitnesstracker.util.rx.SchedulerProviderImpl
import main.com.fitnesstracker.views.activeness.ActivenessViewModel
import main.com.fitnesstracker.views.exercise.ExerciseViewModel
import main.com.fitnesstracker.views.main.MainViewModel
import main.com.fitnesstracker.views.statistic.StatisticsViewModel
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