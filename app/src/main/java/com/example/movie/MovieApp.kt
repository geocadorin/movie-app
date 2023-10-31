package com.example.movie

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class MovieApp : Application() {
    override fun onCreate() {
        super.onCreate()
        //Configura log de debug
        if(BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }

    }
}