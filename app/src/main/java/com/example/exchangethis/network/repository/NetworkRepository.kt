package com.example.exchangethis.network.repository

interface NetworkRepository {
    suspend fun getBookImage(
        query: String
    ): String
}