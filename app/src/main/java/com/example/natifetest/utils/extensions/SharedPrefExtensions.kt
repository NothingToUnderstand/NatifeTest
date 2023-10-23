package com.example.natifetest.utils.extensions

import android.annotation.SuppressLint
import android.content.SharedPreferences

fun SharedPreferences.putString(key: String, value: String?) {
    edit().putString(key, value).apply()
}

fun SharedPreferences.putInt(key: String, value: Int) {
    edit().putInt(key, value).apply()
}

fun SharedPreferences.putBoolean(key: String, value: Boolean) {
    edit().putBoolean(key, value).apply()
}

fun SharedPreferences.putLong(key: String, value: Long) {
    edit().putLong(key, value).apply()
}

fun SharedPreferences.putStringSet(key: String, value: Set<String>?) {
    this.edit().putStringSet(key, value).apply()
}

@SuppressLint("MutatingSharedPrefs")
fun SharedPreferences.addToSet(key: String, value: String) {
    val set = this.getStringSet(key, null) ?: mutableSetOf()
    set.add(value)
    this.edit().putStringSet(key, set).apply()
}

fun SharedPreferences.clearPreferences() {
    edit().clear().apply()
}