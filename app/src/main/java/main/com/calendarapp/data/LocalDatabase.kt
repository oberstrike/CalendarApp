package main.com.calendarapp.data


import android.content.Context
import android.util.Log
import io.objectbox.BoxStore
import io.objectbox.android.AndroidObjectBrowser
import main.com.calendarapp.BuildConfig
import main.com.calendarapp.models.MyObjectBox

object ObjectBox {
    lateinit var boxStore: BoxStore
        private set

    fun init(context: Context){
        boxStore = MyObjectBox.builder().androidContext(context.applicationContext).build()

        if (BuildConfig.DEBUG) {
            Log.d("Debug", "Using ObjectBox ${BoxStore.getVersion()} (${BoxStore.getVersionNative()})")
            AndroidObjectBrowser(boxStore).start(context.applicationContext)
        }
    }
}

