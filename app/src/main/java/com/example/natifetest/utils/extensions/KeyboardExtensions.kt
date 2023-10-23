package com.example.natifetest.utils.extensions

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment

fun Activity.showSoftKeyboard(view: View) {
    if (view.requestFocus()) {
        val imm: InputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
    }
}

fun Activity.hideSoftKeyboard(view: View) {
    val imm =
        getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}

fun Fragment.hideSoftKeyboard(view: View) {
    requireActivity().hideSoftKeyboard(view)
}

fun Fragment.showSoftKeyboard(view: View) {
    requireActivity().showSoftKeyboard(view)
}