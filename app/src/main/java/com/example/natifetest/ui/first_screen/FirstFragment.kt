package com.example.natifetest.ui.first_screen


import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import androidx.core.view.isVisible
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
import com.example.natifetest.databinding.LayoutRvItemGifBinding
import com.example.natifetest.ui.adapter.FooterLoadStateAdapter
import com.example.natifetest.ui.adapter.GifsAdapter
import com.example.natifetest.ui.adapter.diffutil.GifsDiffUtilItemsCallBack
import com.example.natifetest.ui.adapter.viewholders.FirstScreenViewHolder
import com.example.natifetest.ui.base.BaseFragment
import com.example.natifetest.utils.delegates.viewBinding
import com.example.natifetest.utils.extensions.disabled
import com.example.natifetest.utils.extensions.enabled
import com.example.natifetest.utils.extensions.hideSoftKeyboard
import com.example.natifetest.utils.extensions.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber


@AndroidEntryPoint
class FirstFragment : BaseFragment(R.layout.fragment_first) {
    private val binding by viewBinding(::bind)
    private val viewModel: FirstScreenViewModel by viewModels()
    private val adapter = GifsAdapter(
        GifsDiffUtilItemsCallBack()
    ) { inflator ->
        FirstScreenViewHolder(
            LayoutRvItemGifBinding.inflate(inflator),
            { id, forever ->
                viewModel.deleteGif(id, forever)
            },
            {
                navigate(
                    FirstFragmentDirections.toSecondFragment(
                        it,
                        binding.search.text?.toString() ?: ""
                    )
                )
            }
        )
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.d("First started")
        observeViewModel()
        viewModel.getGifsSearch("")
        bindView()

        adapter.addLoadStateListener {
            if (it.refresh is LoadState.Error) {
                Timber.d(
                    (it.refresh as LoadState.Error).error.message
                        ?: getString(R.string.something_went_wrong)
                )
                showToast(
                    (it.refresh as LoadState.Error).error.message
                        ?: getString(R.string.something_went_wrong)
                )
            }
            binding.swiperefresh.isRefreshing = it.refresh is LoadState.Loading
        }
    }

    private fun bindView() = with(binding) {
        binding.swiperefresh.isEnabled = false
        gifsRecyclerview.adapter = adapter.withLoadStateFooter(FooterLoadStateAdapter())
        swiperefresh.setOnRefreshListener {
            Timber.d("Refresh")
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
                Timber.d("Search $it")
                viewModel.getGifsSearch(it.toString())
                gifsRecyclerview.layoutManager?.scrollToPosition(0)
            }
        }
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.gifs.collectLatest {
                    binding.noData.root.isVisible = it == null || adapter.itemCount == 0
                    binding.swiperefresh.isEnabled = it != null && adapter.itemCount > 0
                    it ?: return@collectLatest
                    Timber.d("Data success")
                    adapter.submitData(it)
                }
            }
        }
    }
}