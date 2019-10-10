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
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import com.google.gson.reflect.TypeToken




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
        service = ServiceGenerator.createService(ActivenessRetrofitService::class.java, "oberstrike", "mewtu123")
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

        println(json)

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


    @Test
    fun test_convert() {
        val string = "[\n" +
                "    {\n" +
                "        \"id\": 1,\n" +
                "        \"name\": \"Training\",\n" +
                "        \"date\": \"2019-10-06T10:28:31.418+02:00\",\n" +
                "        \"exercises\": [\n" +
                "            {\n" +
                "                \"id\": 1,\n" +
                "                \"name\": \"Übung\",\n" +
                "                \"workoutSets\": [\n" +
                "                    {\n" +
                "                        \"id\": 2,\n" +
                "                        \"repetitions\": 10,\n" +
                "                        \"weight\": 10\n" +
                "                    }\n" +
                "                ]\n" +
                "            }\n" +
                "        ]\n" +
                "    }\n" +
                "]"
        val type = object : TypeToken<List<Activeness>>(){}.type

        val obj = gson.fromJson<List<Activeness>>(string, type)

        println(obj)


    }

}
