package com.example.exchangethis.network

import com.example.exchangethis.network.dto.BookResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface BookApi {
    @GET("search.json")
    suspend fun getBookImage(
        @Query("q") title: String
    ): BookResponse
}