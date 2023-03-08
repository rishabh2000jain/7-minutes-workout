package com.example.a7minuteworkout.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_history")
data class History(
    @PrimaryKey(true) val uid: Int=0,

    @ColumnInfo(name = "date") val date:Long,
)