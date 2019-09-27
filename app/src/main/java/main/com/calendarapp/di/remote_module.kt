package main.com.calendarapp.di

import main.com.calendarapp.data.ActivenessRetrofitService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.context.startKoin
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import kotlin.math.log

val remote_module = module {
    single { createOkHttpClient() }
    single { createWebServcie<ActivenessRetrofitService>(get(), "localhost") }
}

fun createOkHttpClient() : OkHttpClient {
    val logging = HttpLoggingInterceptor()
    logging.level = HttpLoggingInterceptor.Level.BASIC


    return OkHttpClient.Builder()
        .connectTimeout(60L, TimeUnit.SECONDS)
        .readTimeout(60L, TimeUnit.SECONDS)
        .addInterceptor(logging)
        .build()
}

inline fun <reified T> createWebServcie(okHttpClient : OkHttpClient, url: String):T{
    val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
       // .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build()
        .build()

    return retrofit.create(T::class.java)
}