package com.thesis.dawhey.shoppingcart.prefs

import android.content.Context
import android.content.SharedPreferences

class PreferencesManager(context: Context) {

    val PREF_NAME = "com.thesis.dawhey.shoppingcart.preferences"
    val KEY_TOKEN = "token"

    val prefs: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    var token: String?
        get() =  "go_to_cart" //prefs.getString(KEY_TOKEN, null)
        set(value) = prefs.edit().putString(KEY_TOKEN, value).apply()


}