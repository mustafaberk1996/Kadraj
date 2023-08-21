package com.example.kadraj.data.repository

import com.example.kadraj.data.entity.User

interface LoginRepository {
    suspend fun login(email: String, password: String): User?
}