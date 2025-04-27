package com.kdbrian.menusmvp

import android.app.Application
import com.kdbrian.menusmvp.di.coreModule
import com.kdbrian.menusmvp.di.menuModule
import com.kdbrian.menusmvp.di.restaurantModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class MenusApp : Application() {
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())

        startKoin {
            androidLogger()
            androidContext(this@MenusApp)
            modules(
                coreModule,
                menuModule,
                restaurantModule
            )
        }
    }
}