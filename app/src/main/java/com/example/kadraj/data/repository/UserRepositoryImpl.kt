package com.example.kadraj.data.repository

import com.example.kadraj.data.dao.UserDao
import com.example.kadraj.data.entity.User
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val userDao: UserDao) : UserRepository {

    override suspend fun getUserById(id: Int): User? {
       return userDao.getUserById(id)
    }

    override suspend fun update(user: User) {
        userDao.update(user)
    }
}