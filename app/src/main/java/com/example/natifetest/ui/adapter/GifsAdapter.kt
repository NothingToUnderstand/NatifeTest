package com.example.natifetest.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import com.example.natifetest.ui.base.BaseViewHolder
import com.example.natifetest.ui.base.InflateViewHolder


class GifsAdapter<T : Any, VH : BaseViewHolder<T>>(
    diffUtilItemCallback: ItemCallback<T>,
    private val inflateViewHolder: InflateViewHolder<VH>
) : PagingDataAdapter<T, VH>(diffUtilItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val layoutInflater = LayoutInflater.from(parent.context)
        return inflateViewHolder.invoke(layoutInflater)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }
}