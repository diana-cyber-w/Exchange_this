package com.example.exchangethis.data.repository

import com.example.exchangethis.data.storage.user.UserDao
import com.example.exchangethis.data.toUser
import com.example.exchangethis.data.toUserEntity
import com.example.exchangethis.domain.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class DataRepositoryImpl(
    private val userDao: UserDao
) : DataRepository {

    override fun getUsers(): Flow<List<User>> {
        return flow {
            val users = userDao.getUsers().map { userEntity ->
                userEntity.toUser()
            }
            emit(users)
        }
    }

    override fun getUserByEmailAndPassword(email: String, password: String): Flow<List<User>> {
        return flow {
            val user = userDao.getUserByEmailAmdPassword(email, password).map { userEntity ->
                userEntity.toUser()
            }
            emit(user)
        }
    }

    override suspend fun insertUser(user: User) {
        withContext(Dispatchers.IO) {
            userDao.insertUser(user.toUserEntity())
        }
    }
}