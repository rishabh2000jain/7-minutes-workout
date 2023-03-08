package com.example.a7minuteworkout.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface HistoryDAO {

    @Query("SELECT * FROM `user_history` WHERE id == :uid")
    suspend fun getHistoryById(uid: Int): History

    @Query("SELECT * FROM `user_history`")
    suspend fun getHistoryList(): Flow<List<History>>

    @Insert
    suspend fun insertHistoryItem(history: History): Unit

    @Delete
    suspend fun deleteHistory(history: History): Unit

}