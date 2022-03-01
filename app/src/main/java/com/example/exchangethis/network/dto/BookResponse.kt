package com.example.exchangethis.network.dto

import com.google.gson.annotations.SerializedName

data class BookResponse(
    @SerializedName("numFound")
    val numFound: Int?,

    @SerializedName("start")
    val start: Int?,

    @SerializedName("numFoundExact")
    val numFoundExact: Boolean?,

    @SerializedName("docs")
    val docs: List<Book>?,

    @SerializedName("num_found")
    val num_found: Int?,

    @SerializedName("q")
    val q: String?,

    @SerializedName("offset")
    val offset: String?
)