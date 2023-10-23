package com.example.natifetest.utils.helpers

import android.content.Context
import com.example.natifetest.utils.extensions.addToSet
import com.example.natifetest.utils.extensions.putStringSet


class SharedPrefHelper(context: Context) {
    companion object {
        private const val PREF_NAME = "preference"
        private const val DELETED_IDS = "deleted_ids"
    }

    private val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    var deletedIds: Set<String>?
        get() = sharedPreferences.getStringSet(DELETED_IDS, null)
        set(value) = sharedPreferences.putStringSet(DELETED_IDS, value)

    fun addToSetDeletedIds(gifId: String) {
        sharedPreferences.addToSet(DELETED_IDS, gifId)
    }
}