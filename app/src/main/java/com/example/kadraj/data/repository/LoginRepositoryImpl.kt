package com.example.kadraj.data.repository

import com.example.kadraj.data.dao.UserDao
import com.example.kadraj.data.entity.User
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(private val userDao: UserDao) : LoginRepository {

    override suspend fun login(email: String, password: String): User? {
        userDao.getUser(email, password)?.let {
            return it
        } ?: run {
            return null
        }
    }
}