package com.thesis.dawhey.shoppingcart.prefs

import android.content.Context
import android.content.SharedPreferences

class PreferencesManager(context: Context) {

    val PREF_NAME = "com.thesis.dawhey.shoppingcart.preferences"
    val KEY_TOKEN = "token"
    val KEY_CART_ID = "cart_id"

    val prefs: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    var token: String?
        get() =  prefs.getString(KEY_TOKEN, null)
        set(value) = prefs.edit().putString(KEY_TOKEN, value).apply()

    var cartId: String?
        get() = prefs.getString(KEY_CART_ID, null)
        set(value) = prefs.edit().putString(KEY_CART_ID, value).apply()


}