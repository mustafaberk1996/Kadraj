package com.example.kadraj.data.repository

import com.example.kadraj.data.entity.User

interface UserRepository {

    suspend fun getUserById(id:Int) :User?

    suspend fun update(user: User)
}