package com.example.jvlite

import android.R.attr.apiKey
import android.app.Application
import com.google.android.libraries.places.api.Places


class MainApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        Places.initialize(applicationContext,"AIzaSyBt9JGHKlo8chWR4LF3M075AuLHlHX3BvE" )
        val placesClient = Places.createClient(this)
    }
}