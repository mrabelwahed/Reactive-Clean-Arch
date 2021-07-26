package com.ramadan.reactivearch

import android.app.Application
import timber.log.Timber

class App  : Application(){

    override fun onCreate() {
        super.onCreate()
        //setup timber for debug only
        if(BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())
    }
}