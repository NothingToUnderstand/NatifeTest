package com.example.natifetest.ui.adapter.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.example.natifetest.data.model.Gif

class GifsDiffUtilItemsCallBack : DiffUtil.ItemCallback<Gif>() {
    override fun areItemsTheSame(oldItem: Gif, newItem: Gif) = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: Gif, newItem: Gif) = oldItem.url == newItem.url
}
