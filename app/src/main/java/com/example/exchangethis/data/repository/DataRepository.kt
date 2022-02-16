package com.example.exchangethis.data.repository

import com.example.exchangethis.domain.User
import kotlinx.coroutines.flow.Flow

interface DataRepository {
    fun getUsers(): Flow<List<User>>

    fun getUserByEmailAndPassword(email: String, password: String): Flow<List<User>>

    suspend fun insertUser(user: User)
}