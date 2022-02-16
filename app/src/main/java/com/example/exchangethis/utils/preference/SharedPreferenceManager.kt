package com.example.exchangethis.utils.preference

interface SharedPreferenceManager {
    fun savePassword(key: String, value: String)

    fun getPassword(key: String): String

    fun saveEmail(key: String, value: String)

    fun getEmail(key: String): String
}