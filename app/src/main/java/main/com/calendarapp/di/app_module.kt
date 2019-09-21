package main.com.calendarapp.di

import main.com.calendarapp.data.ActivenessRepo
import main.com.calendarapp.data.ActivenessRepoImpl
import main.com.calendarapp.data.Datasource
import main.com.calendarapp.data.local.FileManager
import main.com.calendarapp.data.local.LocalDatasource
import main.com.calendarapp.data.local.LocalFileManager
import main.com.calendarapp.util.rx.SchedulerProvider
import main.com.calendarapp.util.rx.SchedulerProviderImpl
import main.com.calendarapp.views.activeness.ActivenessViewModel
import main.com.calendarapp.views.main.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module


val calender_module = module {

    single { LocalFileManager(androidContext()) } bind  FileManager::class

    single {LocalDatasource(get())} bind Datasource::class

    single { ActivenessRepoImpl(get())} bind ActivenessRepo::class

    viewModel { MainViewModel(get(), get(), get()) }

    viewModel { ActivenessViewModel(get(), get(), get()) }

    single { SchedulerProviderImpl() } bind SchedulerProvider::class


}


var appModules = listOf(calender_module)