package com.pawels96.skyrimperkcalculator

import android.content.Context
import com.pawels96.skyrimperkcalculator.data.AppDatabase
import com.pawels96.skyrimperkcalculator.data.DefaultBuildRepository
import com.pawels96.skyrimperkcalculator.data.Preferences
import com.pawels96.skyrimperkcalculator.presentation.viewmodels.BuildsViewModel

object Injector {

    lateinit var prefs: Preferences

    private lateinit var db: AppDatabase
    private lateinit var repo: DefaultBuildRepository

    fun init(context: Context) {
        db = AppDatabase.getDatabase(context)
        prefs = Preferences(context.getSharedPreferences("prefs", Context.MODE_PRIVATE))
        repo = DefaultBuildRepository(db.buildDAO())
    }

    fun provideVmFactory() = BuildsViewModel.Factory(repo, prefs)
}