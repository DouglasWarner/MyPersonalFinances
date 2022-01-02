package com.douglas.mypersonalfinances

import android.app.Application
import android.content.Context

class MpfApplication : Application() {

    val getContext: Context by lazy {
        applicationContext
    }

    override fun onCreate() {
        super.onCreate()

        //TODO set local db

        //TODO set firebase

    }
}