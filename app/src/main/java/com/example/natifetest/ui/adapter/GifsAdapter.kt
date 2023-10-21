package com.example.natifetest.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.natifetest.R
import com.example.natifetest.data.model.Gif
import com.example.natifetest.databinding.LayoutRvItemGifBinding

class GifsAdapter : PagingDataAdapter<Gif, GifsAdapter.GifsViewHolder>(Companion) {
    companion object : DiffUtil.ItemCallback<Gif>() {
        override fun areItemsTheSame(
            oldItem: Gif,
            newItem: Gif
        ): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: Gif,
            newItem: Gif
        ): Boolean =
            oldItem.url == newItem.url
    }

    inner class GifsViewHolder(private val binding: LayoutRvItemGifBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(gif: Gif) {
            Glide.with(binding.root.context)
                .load(gif.url)
                .placeholder(R.drawable.loading_placeholder_animated)
                .error(R.drawable.no_image_placeholder)
                .into(binding.gifIv)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GifsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return GifsViewHolder(LayoutRvItemGifBinding.inflate(layoutInflater))
    }

    override fun onBindViewHolder(holder: GifsViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }


}