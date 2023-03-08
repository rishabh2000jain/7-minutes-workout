package com.example.a7minuteworkout.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(version = 1, entities = [History::class])
abstract class HistoryDatabase : RoomDatabase() {
    companion object {
        @Volatile
        private var INSTANCE: HistoryDatabase? = null
        fun getDatabase(context: Context):HistoryDatabase {
            synchronized(this){
                INSTANCE = INSTANCE ?: Room.databaseBuilder(context.applicationContext, HistoryDatabase::class.java, "user_history_database")
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return INSTANCE!!
        }
    }

    abstract fun getDao():HistoryDAO
}
