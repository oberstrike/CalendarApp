package main.com.fitnesstracker.retrofit

import main.com.fitnesstracker.data.WordRetrofitService
import main.com.fitnesstracker.util.retrofit.ServiceGenerator
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException


class WordRetrofitServiceTest {

   private lateinit var apiServiceWithAuth: WordRetrofitService
    private val word_id = 11L


    @Before
    fun setup() {
       apiServiceWithAuth =ServiceGenerator.createService(WordRetrofitService::class.java, "oberstrike", "mewtu123")
    }


    @Test
    fun testGetWordWithBasicAuth(){
        val recWord = apiServiceWithAuth.getWord(word_id).blockingFirst()
        Assert.assertNotNull(recWord)
    }

    @Test
    fun testGetRandomWordWithBasicAuth(){
        val recWords = apiServiceWithAuth.getRandomWord("a", 10).blockingFirst()
        Assert.assertEquals(10, recWords.size)
    }





}
