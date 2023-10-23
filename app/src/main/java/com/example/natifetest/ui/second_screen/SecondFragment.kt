package com.example.natifetest.ui.second_screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.natifetest.R
import com.example.natifetest.databinding.FragmentSecondBinding.bind
import com.example.natifetest.ui.base.BaseFragment
import com.example.natifetest.utils.delegates.viewBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber

class SecondFragment:BaseFragment(R.layout.fragment_second) {
    private val binding by viewBinding(::bind)
    private val viewModel:SecondScreenViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()

    }
    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.gifs.collectLatest {
                    it ?: return@collectLatest
                    Timber.d("Data success screen 2")
//                    adapter.submitData(it)
                }
            }
        }
    }

}