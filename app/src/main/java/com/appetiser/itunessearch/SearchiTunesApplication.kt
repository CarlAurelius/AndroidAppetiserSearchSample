package com.appetiser.itunessearch

import android.app.Application
import com.orhanobut.hawk.Hawk

class SearchiTunesApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        Hawk.init(this).build()
    }

    companion object {
        lateinit var instance: SearchiTunesApplication
            private set
    }

}