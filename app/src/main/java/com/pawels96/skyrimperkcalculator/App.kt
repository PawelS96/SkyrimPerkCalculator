package com.pawels96.skyrimperkcalculator

import android.app.Application
import android.os.Environment

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        val storage = Environment.getExternalStorageDirectory().path
        Thread.setDefaultUncaughtExceptionHandler(CrashHandler(storage))

        Injector.init(this)
    }
}