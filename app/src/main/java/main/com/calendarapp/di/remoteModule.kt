package main.com.calendarapp.di

import main.com.calendarapp.data.ActivenessRetrofitService
import main.com.calendarapp.util.retrofit.ServiceGenerator
import org.koin.dsl.module

val remoteModule = module {
    single { createWebService() }
}

fun createWebService():ActivenessRetrofitService{
    return ServiceGenerator.createService(ActivenessRetrofitService::class.java, "oberstrike", "mewtu123")

}