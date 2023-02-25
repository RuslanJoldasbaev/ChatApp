package com.example.chatapp.data.local

import android.content.Context
import com.example.chatapp.app.App
import com.example.chatappwithfirebase.utils.StringPreference

class LocalStorage {

    companion object {
        val pref =
            App.instance.getSharedPreferences("ChatAppSharedPref", Context.MODE_PRIVATE)
    }

    var username by StringPreference(pref, "temp001")
}