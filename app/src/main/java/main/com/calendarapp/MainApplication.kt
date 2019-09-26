package main.com.calendarapp

import android.app.Application
import android.os.Build
import io.objectbox.android.AndroidObjectBrowser
import main.com.calendarapp.di.appModules
import main.com.calendarapp.ext.ObjectBox
import main.com.calendarapp.models.MyObjectBox
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()


        startKoin {
            // Koin Android logger
            androidLogger()
            //inject Android context
            androidContext(this@MainApplication)
            // use modules
            modules(appModules)
        }

    }

}