package com.pawels96.skyrimperkcalculator.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.pawels96.skyrimperkcalculator.domain.Build
import com.pawels96.skyrimperkcalculator.domain.PerkSystem

@Database(entities = [BuildEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun buildDAO(): BuildDAO

    companion object {

        fun getDatabase(context: Context): AppDatabase {

            return Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "skyrim_calculator_db")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
        }

        fun getInMemoryDatabase(context: Context): AppDatabase {
            return Room.inMemoryDatabaseBuilder(context.applicationContext, AppDatabase::class.java)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
        }
    }
}