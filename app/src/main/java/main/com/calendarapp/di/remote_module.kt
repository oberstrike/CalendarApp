package main.com.calendarapp.di

import main.com.calendarapp.data.ActivenessRetrofitService
import main.com.calendarapp.util.retrofit.ServiceGenerator
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.context.startKoin
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import kotlin.math.log

val remote_module = module {
    single { createWebService() }
}

fun createWebService():ActivenessRetrofitService{
    return ServiceGenerator.createService(ActivenessRetrofitService::class.java, "oberstrike", "mewtu123")

}