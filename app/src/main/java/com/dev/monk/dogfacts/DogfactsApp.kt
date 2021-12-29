package com.dev.monk.dogfacts

import android.app.Application
import com.dev.monk.dogfacts.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class DogfactsApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@DogfactsApp)
            modules(listOf(appModule, remoteModule, localModule, viewModelsModule))
        }

        Timber.plant(Timber.DebugTree())
    }
}
