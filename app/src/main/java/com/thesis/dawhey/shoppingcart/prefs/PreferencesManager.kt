package com.thesis.dawhey.shoppingcart.prefs

import android.content.Context
import android.content.SharedPreferences

class PreferencesManager(context: Context) {

    private val PREF_NAME = "com.thesis.dawhey.shoppingcart.preferences"
    private val KEY_TOKEN = "token"
    private val KEY_DEVICE_ID = "device_id"

    private val prefs: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    var token: String
        get() =  prefs.getString(KEY_TOKEN, "")
        set(value) = prefs.edit().putString(KEY_TOKEN, value).apply()

    var deviceId: String
        get() = prefs.getString(KEY_DEVICE_ID, "")
        set(value) = prefs.edit().putString(KEY_DEVICE_ID, value).apply()


}