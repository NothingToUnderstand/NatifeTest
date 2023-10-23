package com.example.natifetest.ui.base

import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

typealias InflateViewHolder<T> = (LayoutInflater) -> T

abstract class BaseViewHolder<T>(binding: ViewBinding) :
    RecyclerView.ViewHolder(binding.root) {
    abstract fun bind(model: T)
}