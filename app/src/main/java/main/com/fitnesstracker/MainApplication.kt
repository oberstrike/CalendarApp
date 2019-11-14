package main.com.fitnesstracker

import android.app.Application
import main.com.fitnesstracker.data.ObjectBox
import main.com.fitnesstracker.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        ObjectBox.init(applicationContext)

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