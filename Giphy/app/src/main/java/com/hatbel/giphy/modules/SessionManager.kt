package com.hatbel.giphy.modules

import android.content.Context
import androidx.core.content.edit

const val QUERY = "query"

class SessionManager(context: Context) {
    private val prefs = context.getSharedPreferences("settings", Context.MODE_PRIVATE)
    var query: String
        get() = prefs.getString(QUERY, "").toString()
        set(value) = prefs.edit {
            putString(QUERY, value)
        }
}