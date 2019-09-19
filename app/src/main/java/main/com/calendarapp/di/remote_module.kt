package main.com.calendarapp.di

import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val remote_module = module {

}

fun createOkHttpClient() : OkHttpClient {
    return OkHttpClient.Builder()
        .connectTimeout(60L, TimeUnit.SECONDS)
        .readTimeout(60L, TimeUnit.SECONDS)
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