package com.rahulverlekar.data.db

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rahulverlekar.data.entities.GhostSighting

@Database(entities = [GhostSighting::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun ghostSightingDao(): GhostSightingDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "ghost-database" // Name of your database file
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
