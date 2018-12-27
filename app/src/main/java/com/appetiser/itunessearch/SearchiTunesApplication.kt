package com.appetiser.itunessearch

import android.app.Application

class SearchiTunesApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: SearchiTunesApplication
            private set
    }

}