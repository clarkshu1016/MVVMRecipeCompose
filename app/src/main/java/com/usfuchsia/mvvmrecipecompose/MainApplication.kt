package com.usfuchsia.mvvmrecipecompose

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy.Builder

@HiltAndroidApp
class MainApplication : Application() {

    override fun onCreate() {

        if (BuildConfig.DEBUG) {
            enableStrictMode()
        }
        super.onCreate()
    }

    private fun enableStrictMode() {
        StrictMode.setThreadPolicy(
            Builder()
                .detectDiskReads()
                .detectDiskWrites()
                .detectNetwork()
                .penaltyLog()
                .build()
        )
    }
}