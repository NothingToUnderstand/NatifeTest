package com.example.natifetest

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import timber.log.Timber

/*
* Splash icon can be not shown when launching from android studio (only on real device)
* to make it visible just close (kill) the app and open it manually from the device launcher
* */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        var keepSplashScreen = true
        installSplashScreen().also {
            it.setKeepOnScreenCondition {
                Timber.i("SplashScreen started")
                keepSplashScreen
            }
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Handler(Looper.getMainLooper()).postDelayed({
            keepSplashScreen = false
            Timber.i("SplashScreen finished")
        }, 3000L)


    }
}