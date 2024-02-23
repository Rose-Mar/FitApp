package com.example.mag.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Training(
    @PrimaryKey val tid: String,
    @ColumnInfo(name = "distance") val distance: String,
    @ColumnInfo(name = "training_time") val trainingTime: String,
    @ColumnInfo(name = "calories") val calories: String,
    @ColumnInfo(name = "avg_pace") val avgTime: String
)
