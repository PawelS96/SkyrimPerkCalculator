package com.pawels96.skyrimperkcalculator

import android.content.Context
import com.pawels96.skyrimperkcalculator.data.Database
import com.pawels96.skyrimperkcalculator.data.Preferences
import com.pawels96.skyrimperkcalculator.presentation.Utils
import com.pawels96.skyrimperkcalculator.presentation.viewmodels.BuildsViewModel

object Injector {

    lateinit var db: Database
    lateinit var prefs: Preferences

    fun init(context: Context) {
        db = Database(context)
        prefs = Preferences(context.getSharedPreferences("prefs", Context.MODE_PRIVATE))
    }

    fun provideVmFactory() = BuildsViewModel.Factory(db, prefs)
}