package com.douglas.mypersonalfinances

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

@HiltAndroidApp
class MpfApplication : Application() {

    companion object {
        val applicationScope = CoroutineScope(SupervisorJob())
    }
}