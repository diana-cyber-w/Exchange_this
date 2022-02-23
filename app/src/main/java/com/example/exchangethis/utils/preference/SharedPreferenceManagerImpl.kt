package com.example.exchangethis.utils.preference

import android.content.Context

class SharedPreferenceManagerImpl(context: Context) : SharedPreferenceManager {

    private val prefs by lazy { context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE) }

    override fun saveBoolean(key: String, value: Boolean) {
        prefs.edit()
            .putBoolean(key, value)
            .apply()
    }

    override fun getBoolean(key: String): Boolean {
        return prefs.getBoolean(key, DEFAULT_BOOLEAN_VALUE)
    }

    override fun saveString(key: String, value: String) {
        prefs.edit()
            .putString(key, value)
            .apply()
    }

    override fun getString(key: String): String {
        return prefs.getString(key, STRING_DEFAULT_VALUE).orEmpty()
    }

    override fun saveInt(key: String, value: Int) {
        prefs.edit()
            .putInt(key, value)
            .apply()
    }

    override fun getInt(key: String): Int {
        return prefs.getInt(key, DEFAULT_INT_VALUE)
    }

    override fun saveFloat(key: String, value: Float) {
        prefs.edit()
            .putFloat(key, value)
            .apply()
    }

    override fun getFloat(key: String): Float {
        return prefs.getFloat(key, DEFAULT_FLOAT_VALUE)
    }

    companion object {
        private const val PREFS_NAME = "SHARED_PREFS_NAME"
        private const val STRING_DEFAULT_VALUE = "No value"
        private const val DEFAULT_BOOLEAN_VALUE = false
        private const val DEFAULT_INT_VALUE = 0
        private const val DEFAULT_FLOAT_VALUE = 0F
    }
}