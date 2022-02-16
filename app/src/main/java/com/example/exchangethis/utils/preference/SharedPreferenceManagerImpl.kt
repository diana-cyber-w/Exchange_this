package com.example.exchangethis.utils.preference

import android.content.Context

class SharedPreferenceManagerImpl(context: Context) : SharedPreferenceManager {

    companion object {
        private const val PREFS_NAME = "SHARED_PREFS_NAME"
        private const val STRING_DEFAULT_VALUE = "No value"
    }

    private val prefs by lazy { context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE) }

    override fun savePassword(key: String, value: String) {
        prefs.edit()
            .putString(key, value)
            .apply()
    }

    override fun getPassword(key: String): String {
        return prefs.getString(key, STRING_DEFAULT_VALUE)!!
    }

    override fun saveEmail(key: String, value: String) {
        prefs.edit()
            .putString(key, value)
            .apply()
    }

    override fun getEmail(key: String): String {
        return prefs.getString(key, STRING_DEFAULT_VALUE)!!
    }
}