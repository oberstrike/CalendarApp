package main.com.fitnesstracker.di

import main.com.fitnesstracker.data.ActivenessRetrofitService
import main.com.fitnesstracker.util.retrofit.ServiceGenerator
import org.koin.dsl.module

val remoteModule = module {
    single { createWebService() }
}

fun createWebService():ActivenessRetrofitService{
    return ServiceGenerator.createService(ActivenessRetrofitService::class.java, "oberstrike", "mewtu123")

}