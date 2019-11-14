package main.com.fitnesstracker.data

import io.reactivex.Observable
import main.com.fitnesstracker.models.Activeness
import retrofit2.http.GET

interface ActivenessRetrofitService {
    @GET("/api/activeness")
    fun getAll(): Observable<List<Activeness>>
}