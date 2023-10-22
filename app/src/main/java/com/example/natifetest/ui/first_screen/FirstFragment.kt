package com.example.natifetest.ui.first_screen


import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import com.example.natifetest.R
import com.example.natifetest.data.network.adapter.GifAdapter
import com.example.natifetest.databinding.FragmentFirstBinding.bind
import com.example.natifetest.ui.adapter.FooterLoadStateAdapter
import com.example.natifetest.ui.adapter.GifsAdapter
import com.example.natifetest.ui.base.BaseFragment
import com.example.natifetest.utils.delegates.viewBinding
import com.example.natifetest.utils.extensions.hideSoftKeyboard
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class FirstFragment : BaseFragment(R.layout.fragment_first) {
    private val binding by viewBinding(::bind)
    private val viewModel: FirstScreenViewModel by viewModels()
    private val adapter = GifsAdapter {
        viewModel.deleteGif(it)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.i("First started")
        observeViewModel()
        bindView()
        viewModel.getGifs()

        adapter.addLoadStateListener {
            if (it.refresh is LoadState.Error) {
                Toast.makeText(
                    requireContext(),
                    (it.refresh as LoadState.Error).error.message
                        ?: getString(R.string.something_went_wrong),
                    Toast.LENGTH_SHORT
                ).show()
            }

            binding.swiperefresh.isRefreshing = it.refresh is LoadState.Loading
        }
    }

    private fun bindView() = with(binding) {
        gifsRecyclerview.adapter = adapter.withLoadStateFooter(FooterLoadStateAdapter())
        swiperefresh.setOnRefreshListener {
            adapter.refresh()
            gifsRecyclerview.layoutManager?.scrollToPosition(0)
        }
        search.apply {
            setOnFocusChangeListener { _, focus ->
                if (!focus) {
                    hideSoftKeyboard(this)
                }
            }
            doAfterTextChanged {
                viewModel.getGifs(it.toString())
                gifsRecyclerview.layoutManager?.scrollToPosition(0)
            }
        }
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.gifs.collectLatest {
                    it ?: return@collectLatest
                    Timber.i("data success")
                    adapter.submitData(it)
                }
            }
        }
    }
}