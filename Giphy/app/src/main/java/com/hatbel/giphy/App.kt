package com.hatbel.giphy

import android.app.Application
import com.hatbel.giphy.modules.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                listOf(appModule)
            )
        }
    }
}