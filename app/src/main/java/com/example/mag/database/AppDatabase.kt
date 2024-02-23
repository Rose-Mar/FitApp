package com.example.mag.database

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [Training::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun userDao(): TrainingDao
}