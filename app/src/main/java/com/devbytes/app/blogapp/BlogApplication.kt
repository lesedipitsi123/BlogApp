package com.devbytes.app.blogapp

import android.app.Application
import com.google.android.material.color.DynamicColors
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BlogApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if (DynamicColors.isDynamicColorAvailable())
            DynamicColors.applyToActivitiesIfAvailable(this)
    }
}