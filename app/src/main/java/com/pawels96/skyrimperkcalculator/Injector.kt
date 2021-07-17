package com.pawels96.skyrimperkcalculator

import android.content.Context
import com.pawels96.skyrimperkcalculator.data.*
import com.pawels96.skyrimperkcalculator.presentation.viewmodels.BuildsViewModel

object Injector {

    lateinit var oldDb: OldDatabase
    lateinit var prefs: Preferences

    private lateinit var db: AppDatabase
    private lateinit var repo: BuildRepository

    fun init(context: Context) {
        db = AppDatabase.getDatabase(context)
        prefs = Preferences(context.getSharedPreferences("prefs", Context.MODE_PRIVATE))
        repo = BuildRepository(db.buildDAO())
        oldDb = OldDatabase(context, repo)
        repo.populate()
    }

    fun provideVmFactory() = BuildsViewModel.Factory(repo, prefs)
}