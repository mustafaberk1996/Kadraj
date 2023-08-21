package com.example.kadraj

import android.content.Context
import androidx.room.Room
import com.example.kadraj.data.AppDatabase

object Database {

    fun getDatabase(context: Context) =
          Room.databaseBuilder(context, AppDatabase::class.java, "my-database").build()

}