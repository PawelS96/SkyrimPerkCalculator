package com.pawels96.skyrimperkcalculator

import android.app.Application

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Injector.init(this)
    }
}