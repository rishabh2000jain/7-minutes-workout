package com.example.a7minuteworkout.application

import android.app.Application
import com.example.a7minuteworkout.db.HistoryDatabase

class SevenMinutesApplication : Application() {
    val database: HistoryDatabase by lazy { HistoryDatabase.getDatabase(context = applicationContext)}

}