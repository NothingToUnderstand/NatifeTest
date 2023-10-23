package com.example.natifetest.ui.adapter.viewholders

import android.net.Uri
import android.view.View
import androidx.appcompat.widget.PopupMenu
import com.example.natifetest.R
import com.example.natifetest.data.model.Gif
import com.example.natifetest.databinding.LayoutRvItemGifBinding
import com.example.natifetest.ui.base.BaseViewHolder
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.drawable.ScalingUtils
import com.facebook.imagepipeline.request.ImageRequest

class FirstScreenViewHolder(
    private val binding: LayoutRvItemGifBinding,
    private val onDelete: (gifId: String, forever: Boolean) -> Unit,
    private val onGifClick: (position: Int) -> Unit,
) : BaseViewHolder<Gif>(binding) {

    override fun bind(model: Gif) {
        binding.optionsBtn.setOnClickListener {
            showMenu(model.id, binding.optionsBtn)
        }
        val imageRequest = ImageRequest.fromUri(Uri.parse(model.url))
        binding.gifIv.apply {
            setOnClickListener {
                onGifClick.invoke(bindingAdapterPosition)
            }
            controller = Fresco.newDraweeControllerBuilder()
                .setImageRequest(imageRequest)
                .setAutoPlayAnimations(true)
                .build()
            hierarchy.actualImageScaleType = ScalingUtils.ScaleType.FIT_CENTER
            hierarchy.setProgressBarImage(R.drawable.loading_placeholder_animated)
            hierarchy.setFailureImage(R.drawable.no_image_placeholder)
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