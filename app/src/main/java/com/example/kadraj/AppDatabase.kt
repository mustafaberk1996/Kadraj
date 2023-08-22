package com.example.kadraj

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.kadraj.Constants.DATABASE_NAME
import com.example.kadraj.data.api.model.Photo
import com.example.kadraj.data.dao.PhotoDao
import com.example.kadraj.data.dao.PhotoFavouriteDao
import com.example.kadraj.data.dao.UserDao
import com.example.kadraj.data.entity.PhotoFavourite
import com.example.kadraj.data.entity.User

@Database(
    entities = [User::class, Photo::class, PhotoFavourite::class], version = 1, exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun photoDao(): PhotoDao
    abstract fun photoFavouriteDao(): PhotoFavouriteDao


    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        operator fun invoke(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME).build()
        }
    }
}