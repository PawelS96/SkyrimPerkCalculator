package com.pawels96.skyrimperkcalculator

import android.app.Application
import com.pawels96.skyrimperkcalculator.presentation.common.verifyStringResources

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Injector.init(this)

        if (BuildConfig.DEBUG) {
            verifyStringResources()
        }
    }
}