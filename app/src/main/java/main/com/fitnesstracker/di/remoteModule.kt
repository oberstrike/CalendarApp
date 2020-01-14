package main.com.fitnesstracker.di

import main.com.fitnesstracker.data.WordRetrofitService
import main.com.fitnesstracker.util.retrofit.ServiceGenerator
import org.koin.dsl.module

val remoteModule = module {
    single { createWebService() }
}

fun createWebService():WordRetrofitService{
    return ServiceGenerator.createService(WordRetrofitService::class.java, "oberstrike", "mewtu123")

}