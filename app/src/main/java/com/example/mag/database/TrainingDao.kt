package com.example.mag.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TrainingDao {
    @Insert
    suspend fun insertTraining(training: Training)

    @Query("SELECT * FROM trainings ORDER BY timestamp DESC")
    suspend fun getAllTrainings(): List<Training>

    @Insert
    fun insertAll(vararg trainings: Training)

    @Delete
    fun delete(training: Training)
}








