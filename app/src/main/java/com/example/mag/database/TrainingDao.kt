package com.example.mag.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TrainingDao {
    @Query("SELECT * FROM training")
    fun getAll(): List<Training>

//    @Query("SELECT * FROM training WHERE tid IN (:trainingIds)")
//    fun loadAllByIds(trainingIds: IntArray): List<Training>


    @Insert
    fun insertAll(vararg trainings: Training)

//
//    @Delete
//    fun delete(training: Training)

}