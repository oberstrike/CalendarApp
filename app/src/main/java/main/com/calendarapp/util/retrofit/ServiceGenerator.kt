package main.com.calendarapp.util.retrofit

import main.com.calendarapp.data.AuthentificationInterceptor
import main.com.calendarapp.util.TextUtils
import okhttp3.Credentials
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


fun createOkHttpClient() : OkHttpClient {
    val logging = HttpLoggingInterceptor()
    logging.level = HttpLoggingInterceptor.Level.BASIC


    return OkHttpClient.Builder()
        .connectTimeout(60L, TimeUnit.SECONDS)
        .readTimeout(60L, TimeUnit.SECONDS)
        .addInterceptor(logging)
        .build()
}

object ServiceGenerator {

    private const val API_BASE_URL = "http://192.168.2.234:8080"

    private val httpClient = OkHttpClient.Builder()

    private val builder = Retrofit.Builder()
        .client(createOkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .baseUrl(API_BASE_URL)

    private var retrofit = builder.build()

    fun <S> createService(serviceClass: Class<S>): S {
        return createService(serviceClass, null, null)
    }

    fun <S> createService(
        serviceClass: Class<S>, username: String?, password: String?
    ): S {
        if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)) {
            val authToken = Credentials.basic(username!!, password!!)
            return createService(serviceClass, authToken)
        }

        return createService(serviceClass, null)
    }

    fun <S> createService(
        serviceClass: Class<S>, authToken: String?
    ): S {
        if (!TextUtils.isEmpty(authToken)) {
            val interceptor = AuthentificationInterceptor(authToken!!)

            if (!httpClient.interceptors().contains(interceptor)) {
                httpClient.addInterceptor(interceptor)

                builder.client(httpClient.build())
                retrofit = builder.build()
            }
        }

        return retrofit.create(serviceClass)
    }
}