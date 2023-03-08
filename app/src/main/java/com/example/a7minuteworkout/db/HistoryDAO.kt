package com.example.a7minuteworkout.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface HistoryDAO {


    @Query("SELECT * FROM `user_history`")
     fun getHistoryList(): Flow<List<History>>

    @Insert
    suspend fun insertHistoryItem(history: History): Unit


}