package com.example.natifetest.ui.adapter

import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.PopupMenu
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.natifetest.R
import com.example.natifetest.data.model.Gif
import com.example.natifetest.databinding.LayoutRvItemGifBinding
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.drawable.ScalingUtils
import com.facebook.imagepipeline.listener.RequestListener
import com.facebook.imagepipeline.request.ImageRequest

class GifsAdapter(
    private val onDelete: (gifId: String, forever: Boolean) -> Unit
) : PagingDataAdapter<Gif, GifsAdapter.GifsViewHolder>(Companion) {
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

            binding.optionsBtn.setOnClickListener {
                showMenu(gif.id, binding.optionsBtn)
            }
            val imageRequest = ImageRequest.fromUri(Uri.parse(gif.url))
            binding.gifIv.apply {
                controller = Fresco.newDraweeControllerBuilder()
                    .setImageRequest(imageRequest)
                    .setAutoPlayAnimations(true)
                    .build()
                hierarchy.actualImageScaleType = ScalingUtils.ScaleType.FIT_CENTER
                hierarchy.setProgressBarImage(R.drawable.loading_placeholder_animated)
                hierarchy.setFailureImage(R.drawable.no_image_placeholder)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GifsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return GifsViewHolder(LayoutRvItemGifBinding.inflate(layoutInflater))
    }

    override fun onBindViewHolder(holder: GifsViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
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