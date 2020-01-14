package main.com.fitnesstracker.di

import main.com.fitnesstracker.data.ObjectBox
import main.com.fitnesstracker.util.rx.SchedulerProvider
import main.com.fitnesstracker.util.rx.SchedulerProviderImpl
import main.com.fitnesstracker.views.main.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

val fitnessModule = module {



    viewModel { MainViewModel(get()) }

    single { SchedulerProviderImpl() } bind SchedulerProvider::class

    single { ObjectBox.init(get ()) }

}


var appModules = listOf(fitnessModule)