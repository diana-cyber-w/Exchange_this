package com.example.exchangethis.network.repository

import android.util.Log
import com.example.exchangethis.network.BookApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NetworkRepositoryImpl(private val bookApi: BookApi) : NetworkRepository {

    override suspend fun getBookImage(query: String): String {
        Log.e(
            "URI", IMAGE_URL_BASE + bookApi.getBookImage(
                title = query
            ).docs?.get(0)?.isbn?.get(0) + "-L.jpg"
        )
        return withContext(Dispatchers.IO) {
            IMAGE_URL_BASE + bookApi.getBookImage(
                title = query
            ).docs?.get(0)?.isbn?.get(0) + "-L.jpg"
        }
    }

    companion object {
        private const val IMAGE_URL_BASE = "https://covers.openlibrary.org/b/isbn/"
    }
}