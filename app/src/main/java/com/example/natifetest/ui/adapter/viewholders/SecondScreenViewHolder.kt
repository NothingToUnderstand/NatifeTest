package com.example.natifetest.ui.adapter.viewholders

import android.graphics.drawable.Drawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.natifetest.R
import com.example.natifetest.data.model.Gif
import com.example.natifetest.databinding.LayoutRvItemGifFullscreenBinding
import com.example.natifetest.ui.base.BaseViewHolder


class SecondScreenViewHolder(
    private val binding: LayoutRvItemGifFullscreenBinding,
    private val onDelete: (gifId: String) -> Unit,
    ) : BaseViewHolder<Gif>(binding) {
    override fun bind(model: Gif) {
        Glide.with(binding.root.context)
            .load(model.url)
            .fitCenter()
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    m: Any?,
                    target: Target<Drawable>,
                    isFirstResource: Boolean
                ): Boolean {
                    onDelete.invoke(model.id)
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable,
                    model: Any,
                    target: Target<Drawable>?,
                    dataSource: DataSource,
                    isFirstResource: Boolean
                ) = false

            })
            .placeholder(R.drawable.loading_placeholder_animated)
            .into(binding.gifIv)
    }
}