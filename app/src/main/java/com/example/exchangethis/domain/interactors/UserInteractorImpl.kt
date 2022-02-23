package com.example.exchangethis.domain.interactors

import com.example.exchangethis.data.repository.DataRepositoryImpl
import com.example.exchangethis.domain.models.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

class UserInteractorImpl(
    private val dataRepositoryImpl: DataRepositoryImpl
) : UserInteractor {
    override fun getUserByEmail(email: String): Flow<List<User>> =
        dataRepositoryImpl.getUserByEmail(email)
            .flowOn(Dispatchers.IO)

    override fun getUsers(): Flow<List<User>> = dataRepositoryImpl.getUsers()
        .flowOn(Dispatchers.IO)

    override fun getUserByEmailAndPassword(email: String, password: String): Flow<List<User>> =
        dataRepositoryImpl.getUserByEmailAndPassword(email, password)
            .flowOn(Dispatchers.IO)

    override suspend fun insertUser(user: User) {
        withContext(Dispatchers.IO) {
            dataRepositoryImpl.insertUser(user)
        }
    }

    override suspend fun updateUser(
        fullName: String,
        phone: String,
        password: String,
        email: String
    ) {
        withContext(Dispatchers.IO) {
            dataRepositoryImpl.updateUser(fullName, phone, password, email)
        }
    }
}