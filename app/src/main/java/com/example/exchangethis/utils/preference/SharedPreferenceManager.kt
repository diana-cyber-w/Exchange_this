package com.example.exchangethis.utils.preference

interface SharedPreferenceManager {
    fun saveString(key: String, value: String)

    fun getString(key: String): String

    fun saveBoolean(key: String, value: Boolean)

    fun getBoolean(key: String): Boolean

    fun saveInt(key: String, value: Int)

    fun getInt(key: String): Int

    fun saveFloat(key: String, value: Float)

    fun getFloat(key: String): Float
}