package com.example.exchangethis.network.dto

import com.google.gson.annotations.SerializedName

data class Book(
    @SerializedName("key")
    val key: String,

    @SerializedName("type")
    val type: String,

    @SerializedName("seed")
    val seed: List<String>,

    @SerializedName("title")
    val title: String,

    @SerializedName("isbn")
    val isbn: List<String>,

    @SerializedName("ia")
    val ia: List<String>,

    @SerializedName("public_scan_b")
    val public_scan_b: Boolean,

    @SerializedName("ia_collection_s")
    val ia_collection_s: String,

    @SerializedName("lending_edition_s")
    val lending_edition_s: String,

    @SerializedName("lending_identifier_s")
    val lending_identifier_s: String,

    @SerializedName("printdisabled_s")
    val printdisabled_s: String,

    @SerializedName("cover_edition_key")
    val cover_edition_key: String,

    @SerializedName("publisher")
    val publisher: List<String>,

    @SerializedName("language")
    val language: List<String>,

    @SerializedName("author_key")
    val author_key: List<String>,

    @SerializedName("author_name")
    val author_name: List<String>,

    @SerializedName("author_alternative_name")
    val author_alternative_name: List<String>
)