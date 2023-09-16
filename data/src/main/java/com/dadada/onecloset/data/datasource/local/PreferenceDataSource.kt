package com.dadada.onecloset.data.datasource.local

import android.content.Context
import android.content.SharedPreferences
import com.dadada.onecloset.domain.model.AccountInfo
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class PreferenceDataSource @Inject constructor(
    @ApplicationContext context: Context
) {
    companion object {
        private const val PREFERENCE_NAME = "preference_name"
        const val ACCOUNT_INFO = "account_info"
    }

    private fun getPreference(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
    }

    private val prefs by lazy { getPreference(context) }
    private val editor by lazy { prefs.edit() }
    private val gson = Gson()

    fun putString(key: String, data: String?) {
        editor.putString(key, data)
        editor.apply()
    }

    private fun putBoolean(key: String, data: Boolean) {
        editor.putBoolean(key, data)
        editor.apply()
    }

    private fun putInt(key: String, data: Int) {
        editor.putInt(key, data)
        editor.apply()
    }

    fun getString(key: String, defValue: String? = null): String? {
        return prefs.getString(key, defValue)
    }

    private fun getBoolean(key: String, defValue: Boolean = false): Boolean {
        return prefs.getBoolean(key, defValue)
    }

    private fun getInt(key: String, defValue: Int = 0): Int {
        return prefs.getInt(key, defValue)
    }

    fun putAccountInfo(accountInfo: AccountInfo) {
        putString(ACCOUNT_INFO, gson.toJson(accountInfo))
    }

    fun getAccountInfo() : AccountInfo? {
        return gson.fromJson(getString(ACCOUNT_INFO), AccountInfo::class.java)
    }

    fun removeAccountInfo() {
        putString(ACCOUNT_INFO, null)
    }

}