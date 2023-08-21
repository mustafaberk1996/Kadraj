package com.example.kadraj.data.dao


import androidx.room.Dao
import androidx.room.Insert
import com.example.kadraj.data.entity.User

@Dao
interface UserDao {

    @Insert
    suspend fun insert(user: User)


}