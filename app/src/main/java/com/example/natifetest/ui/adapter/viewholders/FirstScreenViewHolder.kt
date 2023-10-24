package com.example.natifetest.ui.adapter.viewholders

import android.graphics.drawable.Drawable
import android.view.View
import androidx.appcompat.widget.PopupMenu
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.natifetest.R
import com.example.natifetest.data.model.Gif
import com.example.natifetest.databinding.LayoutRvItemGifBinding
import com.example.natifetest.ui.base.BaseViewHolder


class FirstScreenViewHolder(
    private val binding: LayoutRvItemGifBinding,
    private val onDelete: (gifId: String, forever: Boolean) -> Unit,
    private val onGifClick: (position: Int) -> Unit,
) : BaseViewHolder<Gif>(binding) {

    override fun bind(model: Gif) {
        binding.optionsBtn.setOnClickListener {
            showMenu(model.id, binding.optionsBtn)
        }

        Glide.with(binding.root.context)
            .load(model.url)
            .centerCrop()
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    m: Any?,
                    target: Target<Drawable>,
                    isFirstResource: Boolean
                ): Boolean {
                    onDelete.invoke(model.id, false)
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

        binding.gifIv.setOnClickListener {
            onGifClick.invoke(bindingAdapterPosition)
        }
    }

    private fun showMenu(gifId: String, view: View) {
        PopupMenu(view.context, view).apply {
            inflate(R.menu.menu)
            setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.action_delete -> {
                        onDelete.invoke(gifId, true)
                        true
                    }

                    else -> false
                }
            }
            show()
        }
    }
}