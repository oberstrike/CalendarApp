package main.com.calendarapp.boxTests

import io.objectbox.BoxStore
import io.objectbox.DebugFlags
import main.com.calendarapp.models.MyObjectBox
import org.junit.After
import org.junit.Before
import java.io.File
import java.lang.Exception

abstract class AbstractObjectBoxTest {

    companion object {
        @JvmStatic
        fun TEST_DIRECTORY() = File("objectbox-example/test-db");
    }

    protected var store: BoxStore? = null


    @Before
    @Throws(Exception::class)
    fun setUp() {
        BoxStore.deleteAllFiles(TEST_DIRECTORY())
        store = MyObjectBox.builder()
            .directory(TEST_DIRECTORY())
            .debugFlags(DebugFlags.LOG_QUERIES)
            .build()
    }

    @After
    fun tearDown() {
        if (store != null) {
            store?.close()
            store = null
        }
        BoxStore.deleteAllFiles(TEST_DIRECTORY())
    }


}