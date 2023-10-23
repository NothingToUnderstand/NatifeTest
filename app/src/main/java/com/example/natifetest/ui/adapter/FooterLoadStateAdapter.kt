package com.example.natifetest.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.natifetest.databinding.LoadStateAdapterFooterLayoutBinding


class FooterLoadStateAdapter : LoadStateAdapter<FooterLoadStateAdapter.LoadStateHolder>() {

    override fun onBindViewHolder(holder: LoadStateHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateHolder {
        val inflater = LayoutInflater.from(parent.context)
        return LoadStateHolder(LoadStateAdapterFooterLayoutBinding.inflate(inflater, parent, false))
    }

    inner class LoadStateHolder(
        private val binding: LoadStateAdapterFooterLayoutBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(loadState: LoadState) = with(binding) {
            messageTextView.isVisible = loadState is LoadState.Error
            if (loadState is LoadState.Error) loadState.error.message?.let {  messageTextView.text = it }
            progressBar.root.isVisible = loadState is LoadState.Loading
        }
    }

}