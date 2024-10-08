package com.example.mag.database
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "trainings")
data class Training(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val timestamp: Long,
    val distance: Float,
    val duration: Long
)

