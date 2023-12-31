package com.example.kadraj

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.kadraj.data.dao.UserDao
import com.example.kadraj.data.entity.User

@Database(
    entities = [User::class], version = 1, exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}