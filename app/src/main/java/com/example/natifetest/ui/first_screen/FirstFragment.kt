package com.example.natifetest.ui.first_screen


import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.LoadState
import com.example.natifetest.R
import com.example.natifetest.data.network.adapter.GifAdapter
import com.example.natifetest.databinding.FragmentFirstBinding.bind
import com.example.natifetest.ui.adapter.FooterLoadStateAdapter
import com.example.natifetest.ui.adapter.GifsAdapter
import com.example.natifetest.ui.base.BaseFragment
import com.example.natifetest.utils.delegates.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class FirstFragment : BaseFragment(R.layout.fragment_first) {
    private val binding by viewBinding(::bind)
    private val viewModel: FirstScreenViewModel by viewModels()
    private val adapter = GifsAdapter()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.i("First started")

        binding.gifsRecyclerview.adapter = adapter.withLoadStateFooter(FooterLoadStateAdapter())

        viewModel.getGifs()

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.gifs.collectLatest {
                    it ?: return@collectLatest
                    Timber.i("data success")
                    adapter.submitData(it)
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED) {
                adapter.loadStateFlow.collectLatest { state ->
                    Log.d("COUNT","${adapter.itemCount}")
//                    when (val loadState = state.refresh) {
//                        is LoadState.Loading -> viewModel.changeStatus(Status.LOADING)
//                        is LoadState.Error -> {
//                            viewModel.changeStatus(Status.ERROR)
//                            viewModel.changeBrandError(
//                                loadState.error.message
//                                    ?: resources.getString(R.string.warning_message_something_went_wrong)
//                            )
//                        }
//
//                        else -> viewModel.changeStatus(Status.SUCCESS)
//                    }
                }
            }
        }
    }
}