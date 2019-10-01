package main.com.calendarapp

import android.text.TextUtils
import com.fatboyindustrial.gsonjodatime.Converters
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import io.reactivex.functions.Consumer
import main.com.calendarapp.boxTests.AbstractObjectBoxTest
import main.com.calendarapp.data.ActivenessRetrofitService
import main.com.calendarapp.models.Activeness
import main.com.calendarapp.testdata.TestData
import main.com.calendarapp.util.retrofit.ServiceGenerator
import okhttp3.internal.wait
import okhttp3.internal.waitMillis
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.powermock.api.mockito.PowerMockito


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class RetrofitGsonTest : AbstractObjectBoxTest(){

    lateinit var webServer: MockWebServer

    lateinit var gson: Gson

    lateinit var service: ActivenessRetrofitService

    @Before
    fun init(){
        PowerMockito.mockStatic(TextUtils::class.java)

        gson = Converters.registerDateTime(GsonBuilder()).create()
        webServer = MockWebServer()
        service = ServiceGenerator.createService(ActivenessRetrofitService::class.java)
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

        val call = service.getAll()
        assertTrue(call != null)

        webServer.shutdown()
    }

    @Test
    fun test_retrofit_combined_with_objectBox() {
        val box = store?.boxFor(Activeness::class.java)

        val call = service.getAll()

        call.doOnError{ println(it.message) }

        call.subscribe{ println(it)}
        assertTrue(  !box?.isEmpty!! )
    }


}
