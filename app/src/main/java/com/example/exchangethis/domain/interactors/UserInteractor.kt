package com.example.exchangethis.domain.interactors

import com.example.exchangethis.domain.models.User
import kotlinx.coroutines.flow.Flow

interface UserInteractor {
    fun getUsers(): Flow<List<User>>

    fun getUserByEmailAndPassword(email: String, password: String): Flow<List<User>>

    fun getUserByEmail(email: String): Flow<List<User>>

    suspend fun insertUser(user: User)

    suspend fun updateUser(
        fullName: String,
        phone: String,
        password: String,
        email: String
    )
}