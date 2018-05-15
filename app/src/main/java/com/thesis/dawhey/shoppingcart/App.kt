package com.thesis.dawhey.shoppingcart

import android.app.Application
import com.thesis.dawhey.shoppingcart.prefs.PreferencesManager

val prefs: PreferencesManager by lazy {
    App.prefs!!
}

class App : Application() {
    companion object {
        var prefs: PreferencesManager? = null
    }

    override fun onCreate() {
        super.onCreate()
        prefs = PreferencesManager(applicationContext)
    }
}