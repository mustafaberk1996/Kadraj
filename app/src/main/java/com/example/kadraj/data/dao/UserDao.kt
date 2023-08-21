package com.example.kadraj.data.dao


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.kadraj.data.entity.User

@Dao
interface UserDao {

    @Insert
    suspend fun insert(user: User)

    @Query("SELECT * FROM user WHERE email = :email AND password = :password")
    suspend fun getUser(email: String, password: String): User?
}