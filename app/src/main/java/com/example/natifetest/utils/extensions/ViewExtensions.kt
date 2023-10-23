package com.example.natifetest.utils.extensions

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.net.Uri
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.view.children
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import timber.log.Timber


fun Fragment.onBackPressedCallback(callback: () -> Unit) {
    requireActivity().onBackPressedDispatcher.addCallback(
        viewLifecycleOwner,
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                callback()
            }
        })
}

fun Fragment.showSnack(message: String) {
    view?.let {
        Snackbar.make(it, message, Snackbar.LENGTH_SHORT).show()
    }
}

fun Fragment.showToast(message: String) {
    view?.let {
        Toast.makeText(it.context, message, Toast.LENGTH_SHORT).show()
    }
}

fun Fragment.redirectToUrl(url: String) =
    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))


fun View.getActivity(): Activity? {
    var context: Context? = this.context
    while (context is ContextWrapper) {
        if (context is Activity) {
            return context
        }
        context = context.baseContext
    }
    return null
}


fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}


fun ViewGroup.disabled() {
    try {
        this.children.forEach {
            if (it is ViewGroup) {
                it.disabled()
            } else {
                it.isClickable = false
                it.isEnabled = false
            }
        }
    } catch (e: Exception) {
        Timber.e("Disabled", e.message.toString(), e)
    }
}

fun ViewGroup.enabled() {
    try {
        this.children.forEach {
            if (it is ViewGroup) {
                it.enabled()
            } else {
                it.isClickable = true
                it.isEnabled = true
            }
        }
    } catch (e: Exception) {
        Timber.e("Enabled", e.message.toString(), e)

    }
}

fun View.snackBar(view: View?, message: String?) {

    view?.let {
        if (!message.isNullOrEmpty()) {
            val snackbar =
                Snackbar
                    .make(
                        it,
                        message.toString(),
                        Snackbar.LENGTH_LONG
                    )
            snackbar.show()
        }
    }
}

