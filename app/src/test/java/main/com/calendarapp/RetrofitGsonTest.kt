package main.com.calendarapp

import com.fatboyindustrial.gsonjodatime.Converters
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import main.com.calendarapp.boxTests.AbstractObjectBoxTest
import main.com.calendarapp.data.ActivenessRetrofitService
import main.com.calendarapp.models.Activeness
import main.com.calendarapp.testdata.TestData
import okhttp3.internal.wait
import okhttp3.internal.waitMillis
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class RetrofitGsonTest : AbstractObjectBoxTest(){

    lateinit var retrofit: Retrofit

    lateinit var webServer: MockWebServer

    lateinit var gson: Gson

    @Before
    fun init(){
        gson = Converters.registerDateTime(GsonBuilder()).create()
        webServer = MockWebServer()
        retrofit = Retrofit
            .Builder()
            .baseUrl(webServer.url("/activeness/all/").toString())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @After
    fun after(){
        webServer.shutdown()
    }

    @Test
    @Throws
    fun test_retrofit(){

        val data = TestData.TEST_1.activeness()
        val json = gson.toJson(data)

        webServer.enqueue(MockResponse().setBody(json))

        val service = retrofit.create(ActivenessRetrofitService::class.java)
        val call = service.getAll()
        assertTrue(call != null)

        webServer.shutdown()
    }

    @Test
    fun test_retrofit_combined_with_objectBox() {
        val box = store?.boxFor(Activeness::class.java)
        val data = TestData.TEST_1.activeness()
        val json = gson.toJson(data)

        webServer.enqueue(MockResponse().setBody(json))

        val service = retrofit.create(ActivenessRetrofitService::class.java)
        val call = service.getAll()

        call.subscribe{it.forEach{ each -> box?.put(each) }}

        assertTrue(  !box?.isEmpty!! )
    }


}
