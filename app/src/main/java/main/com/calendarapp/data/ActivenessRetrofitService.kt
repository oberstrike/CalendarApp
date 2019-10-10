package main.com.calendarapp.data

import io.reactivex.Observable
import main.com.calendarapp.models.Activeness
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface ActivenessRetrofitService {

    @GET("/api/activeness")
    fun getAll(): Observable<List<Activeness>>

}