package com.example.exchangethis.data

import com.example.exchangethis.data.storage.user.UserEntity
import com.example.exchangethis.domain.User

fun UserEntity.toUser() =
    User(
        fullName = fullName,
        email = email,
        phone = phone,
        password = password
    )

fun User.toUserEntity() =
    UserEntity(
        fullName = fullName,
        email = email,
        phone = phone,
        password = password
    )