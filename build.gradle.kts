buildscript {
    repositories {
        google()
        maven {
            url = uri("https://jitpack.io")
        }
        mavenCentral()
    }
}
plugins {
    alias(libs.plugins.com.android.library) apply false
    alias(libs.plugins.com.android.app) apply false
    alias(libs.plugins.safe.args) apply false
    alias(libs.plugins.kotlin) apply false
    alias(libs.plugins.hilt) apply false
}
