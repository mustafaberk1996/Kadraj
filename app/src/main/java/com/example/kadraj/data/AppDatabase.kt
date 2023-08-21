package com.example.kadraj.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.kadraj.data.dao.UserDao
import com.example.kadraj.data.entity.User

@Database(
    entities = [User::class], // bu liste demek, bütün tablolarımı ekleyebilirim içine [User::class, Book:class] gibi
    version = 1
)

abstract class AppDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao

}