package main.com.calendarapp.data

import io.reactivex.Observable
import main.com.calendarapp.models.Activeness
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface ActivenessRetrofitService {

    @GET("/activeness/all")
    @Headers("Content-type: application/json")
    fun getAll(@Header("Authorization") authHeader: String): Observable<List<Activeness>>

}