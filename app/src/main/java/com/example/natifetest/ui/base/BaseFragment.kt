package com.example.natifetest.ui.base


import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.example.natifetest.utils.helpers.getNavOptions

abstract class BaseFragment(@LayoutRes layoutRes: Int) : Fragment(layoutRes) {

    fun navigate(direction: NavDirections) {
        findNavController().navigate(direction, getNavOptions())
    }

    fun navigateBack() {
        findNavController().popBackStack()
    }
}