package com.bikebuka.bikebuka

import android.app.Application
import com.google.firebase.analytics.FirebaseAnalytics
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BikeBuka : Application() {
    lateinit var firebaseAnalytics: FirebaseAnalytics

    override fun onCreate() {
        super.onCreate()
        firebaseAnalytics = FirebaseAnalytics.getInstance(this)
    }
}