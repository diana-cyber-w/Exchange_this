package com.example.exchangethis.domain

import com.example.exchangethis.data.repository.DataRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

class UserInteractorImpl(
    private val dataRepositoryImpl: DataRepositoryImpl
) : UserInteractor {

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
}