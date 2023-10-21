package com.example.natifetest.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.natifetest.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

/*
* Splash icon can be not shown when launching from android studio (only on real device)
* to make it visible just close (kill) the app and open it manually from the device launcher
* */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        var keepSplashScreen = true
        Timber.i("SplashScreen started")
        installSplashScreen().also {
            it.setKeepOnScreenCondition {
                keepSplashScreen
            }
        }
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        Handler(Looper.getMainLooper()).postDelayed({
            keepSplashScreen = false
            Timber.i("SplashScreen finished")
        }, 3000L)


    }
}