package com.example.natifetest.ui.first_screen


import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.natifetest.R
import com.example.natifetest.databinding.FragmentFirstBinding.bind
import com.example.natifetest.ui.base.BaseFragment
import com.example.natifetest.utils.delegates.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class FirstFragment : BaseFragment(R.layout.fragment_first) {
    private val binding by viewBinding(::bind)
    private val viewModel: FirstScreenViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.i("First started")
    }
}