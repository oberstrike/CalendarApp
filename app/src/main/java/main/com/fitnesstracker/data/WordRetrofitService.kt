package main.com.fitnesstracker.data

import io.reactivex.Observable
import main.com.fitnesstracker.models.Word
import retrofit2.http.GET
import retrofit2.http.Query

interface WordRetrofitService {
    @GET("/api/words")
    fun getWord (@Query("id") id: Long ): Observable<Word>

    @GET("/api/words/random")
    fun getRandomWord(@Query("firstletter") firstLetter: String,
                      @Query("count") count: Int
                      ): Observable<List<Word>>
}