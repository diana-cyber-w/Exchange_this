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

//    @SerializedName("title_suggest")
//    val title_suggest: String,
//
//    @SerializedName("has_fulltext")
//    val has_fulltext: Boolean,
//
//    @SerializedName("edition_count")
//    val edition_count: Int,
//
//    @SerializedName("edition_key")
//    val edition_key: List<String>,
//
//    @SerializedName("publish_date")
//    val publish_date: List<String>,
//
//    @SerializedName("publish_year")
//    val publish_year: List<Int>,
//
//    @SerializedName("first_publish_year")
//    val first_publish_year: Int,
//
//    @SerializedName("number_of_pages_median")
//    val number_of_pages_median: Int,
//
//    @SerializedName("lccn")
//    val lccn: List<Int>,
//
//    @SerializedName("publish_place")
//    val publish_place: List<String>,
//
//    @SerializedName("oclc")
//    val oclc: List<Int>,
//
//    @SerializedName("contributor")
//    val contributor: List<String>,
//
//    @SerializedName("lcc")
//    val lcc: List<String>,
//
//    @SerializedName("ddc")
//    val ddc: List<Double>,

    @SerializedName("isbn")
    val isbn: List<String>,

//    @SerializedName("last_modified_i")
//    val last_modified_i: Int,
//
//    @SerializedName("ebook_count_i")
//    val ebook_count_i: Int,

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

//    @SerializedName("cover_i")
//    val cover_i: Int,
//
//    @SerializedName("first_sentence")
//    val first_sentence: String,

    @SerializedName("publisher")
    val publisher: List<String>,

    @SerializedName("language")
    val language: List<String>,

    @SerializedName("author_key")
    val author_key: List<String>,

    @SerializedName("author_name")
    val author_name: List<String>,

    @SerializedName("author_alternative_name")
    val author_alternative_name: List<String>,

//    @SerializedName("person")
//    val person: List<String>,
//
//    @SerializedName("place")
//    val place: List<String>,
//
//    @SerializedName("subject")
//    val subject: List<String>,
//
//    @SerializedName("time")
//    val time: List<String>,
//
//    @SerializedName("id_alibris_id")
//    val id_alibris_id: List<Int>,
//
//    @SerializedName("id_amazon")
//    val id_amazon: List<String>,
//
//    @SerializedName("id_bcid")
//    val id_bcid: List<Int>,
//
//    @SerializedName("id_depósito_legal")
//    val id_depósito_legal: List<String>,
//
//    @SerializedName("id_dnb")
//    val id_dnb: List<String>,
//
//    @SerializedName("id_goodreads")
//    val id_goodreads: List<Int>,
//
//    @SerializedName("id_google")
//    val id_google: List<String>,
//
//    @SerializedName("id_librarything")
//    val id_librarything: List<Int>,
//
//    @SerializedName("id_libris")
//    val id_libris: List<Int>,
//
//    @SerializedName("id_overdrive")
//    val id_overdrive: List<String>,
//
//    @SerializedName("id_wikidata")
//    val id_wikidata: List<String>,
//
//    @SerializedName("ia_loaded_id")
//    val ia_loaded_id: List<String>,
//
//    @SerializedName("ia_box_id")
//    val ia_box_id: List<String>,
//
//    @SerializedName("publisher_facet")
//    val publisher_facet: List<String>,
//
//    @SerializedName("person_key")
//    val person_key: List<String>,
//
//    @SerializedName("place_key")
//    val place_key: List<String>,
//
//    @SerializedName("time_facet")
//    val time_facet: List<String>,
//
//    @SerializedName("person_facet")
//    val person_facet: List<String>,
//
//    @SerializedName("subject_facet")
//    val subject_facet: List<String>,
//
//    @SerializedName("_version_")
//    val _version_: Int,
//
//    @SerializedName("place_facet")
//    val place_facet: List<String>,
//
//    @SerializedName("lcc_sort")
//    val lcc_sort: String,
//
//    @SerializedName("author_facet")
//    val author_facet: List<String>,
//
//    @SerializedName("subject_key")
//    val subject_key: List<String>,
//
//    @SerializedName("ddc_sort")
//    val ddc_sort: Double,
//
//    @SerializedName("time_key")
//    val time_key: List<String>
)