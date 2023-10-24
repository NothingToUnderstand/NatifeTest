package com.example.natifetest.ui.second_screen

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import androidx.paging.LoadState
import com.example.natifetest.R
import com.example.natifetest.databinding.FragmentSecondBinding.bind
import com.example.natifetest.databinding.LayoutRvItemGifFullscreenBinding
import com.example.natifetest.ui.adapter.GifsAdapter
import com.example.natifetest.ui.adapter.diffutil.GifsDiffUtilItemsCallBack
import com.example.natifetest.ui.adapter.viewholders.SecondScreenViewHolder
import com.example.natifetest.ui.base.BaseFragment
import com.example.natifetest.utils.delegates.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber


@AndroidEntryPoint
class SecondFragment : BaseFragment(R.layout.fragment_second) {

    private val binding by viewBinding(::bind)
    private val viewModel: SecondScreenViewModel by viewModels()
    private val args: SecondFragmentArgs by navArgs()

    private val adapter = GifsAdapter(
        GifsDiffUtilItemsCallBack()
    ) { inflator ->
        SecondScreenViewHolder(LayoutRvItemGifFullscreenBinding.inflate(inflator)) {
            viewModel.deleteGif(it)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        observeAdapter()
        viewModel.getGifs(args.search)
        binding.gifsRecyclerview.adapter = adapter

    }

    private fun observeAdapter() {
        var first = true
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED) {
                adapter.loadStateFlow.collectLatest { state ->
                    if (state.refresh == LoadState.Loading && first) {
                        binding.gifsRecyclerview.layoutManager?.scrollToPosition(args.position)
                        first = false
                    }
                }
            }
        }
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.gifs.collectLatest {
                    it ?: return@collectLatest
                    Timber.d("Data success screen 2")
                    adapter.submitData(it)

                }
            }
        }
    }

}