package main.com.fitnesstracker.util.retrofit

import main.com.fitnesstracker.data.AuthentificationInterceptor
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

    private const val API_BASE_URL = "http://192.168.2.112:8080"

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
        if (username != null && password != null) {
            val authToken = Credentials.basic(username!!, password!!)
            return createService(serviceClass, authToken)
        }

        return createService(serviceClass, null)
    }

    fun <S> createService(
        serviceClass: Class<S>, authToken: String?
    ): S {
        if (authToken != null) {
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