package com.example.natifetest.utils.helpers

import androidx.navigation.NavOptions
import com.example.natifetest.R


fun getNavOptions(): NavOptions {
    return NavOptions.Builder()
        .setEnterAnim(R.anim.nav_default_enter_anim)
        .setExitAnim(R.anim.nav_default_exit_anim)
        .setPopEnterAnim(R.anim.nav_default_pop_enter_anim)
        .setPopExitAnim(R.anim.nav_default_pop_exit_anim)
        .build()
}
fun getNavOptionsSingleTop(): NavOptions {
    return NavOptions.Builder()
        .setEnterAnim(R.anim.nav_default_enter_anim)
        .setExitAnim(R.anim.nav_default_exit_anim)
        .setPopEnterAnim(R.anim.nav_default_pop_enter_anim)
        .setPopExitAnim(R.anim.nav_default_pop_exit_anim)
        .setLaunchSingleTop(true)
        .build()
}

fun customNavOptions(popBackStackId: Int, inclusive: Boolean): NavOptions {
    return NavOptions.Builder()
        .setEnterAnim(R.anim.nav_default_enter_anim)
        .setExitAnim(R.anim.nav_default_exit_anim)
        .setPopEnterAnim(R.anim.nav_default_pop_enter_anim)
        .setPopExitAnim(R.anim.nav_default_pop_exit_anim)
        .setPopUpTo(popBackStackId, inclusive)
        .build()
}

fun customNavOptionsSingleTop(popBackStackId: Int, inclusive: Boolean): NavOptions {
    return NavOptions.Builder()
        .setEnterAnim(R.anim.nav_default_enter_anim)
        .setExitAnim(R.anim.nav_default_exit_anim)
        .setPopEnterAnim(R.anim.nav_default_pop_enter_anim)
        .setPopExitAnim(R.anim.nav_default_pop_exit_anim)
        .setPopUpTo(popBackStackId, inclusive)
        .setLaunchSingleTop(true)
        .build()
}

fun getNavOptionsFromBottomNonStay(): NavOptions {
    return NavOptions.Builder()
        .setEnterAnim(R.anim.nav_bottom_enter_anim)
        .setExitAnim(R.anim.nav_bottom_exit_anim)
        .setPopEnterAnim(R.anim.nav_bottom_pop_enter)
        .setPopExitAnim(R.anim.nav_bottom_pop_exit)
        .build()
}

fun getNavOptionsFromBottom(): NavOptions {
    return NavOptions.Builder()
        .setEnterAnim(R.anim.nav_bottom_enter_anim)
        .setExitAnim(R.anim.nav_bottom_stay_anim)
        .setPopExitAnim(R.anim.nav_bottom_pop_exit)
        .build()
}

fun customNavOptionsFromBottom(popBackStackId: Int, inclusive: Boolean): NavOptions {
    return NavOptions.Builder()
        .setEnterAnim(R.anim.nav_bottom_enter_anim)
        .setExitAnim(R.anim.nav_bottom_exit_anim)
        .setPopEnterAnim(R.anim.nav_bottom_pop_enter)
        .setPopExitAnim(R.anim.nav_bottom_pop_exit)
        .setPopUpTo(popBackStackId, inclusive)
        .build()
}

fun getNavOptionsFade(): NavOptions {
    return NavOptions.Builder()
        .setEnterAnim(android.R.anim.fade_in)
        .setPopEnterAnim(android.R.anim.fade_in)
        .build()
}