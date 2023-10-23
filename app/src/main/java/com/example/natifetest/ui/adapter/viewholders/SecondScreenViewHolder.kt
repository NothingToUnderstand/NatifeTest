package com.example.natifetest.ui.adapter.viewholders

import android.net.Uri
import com.example.natifetest.R
import com.example.natifetest.data.model.Gif
import com.example.natifetest.databinding.LayoutRvItemGifFullscreenBinding
import com.example.natifetest.ui.base.BaseViewHolder
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.drawable.ScalingUtils
import com.facebook.imagepipeline.request.ImageRequest

class SecondScreenViewHolder(
    private val binding: LayoutRvItemGifFullscreenBinding
) : BaseViewHolder<Gif>(binding) {
    override fun bind(model: Gif) {
        binding.gifIv.apply {
            controller = Fresco.newDraweeControllerBuilder()
                .setImageRequest(ImageRequest.fromUri(Uri.parse(model.url)))
                .setAutoPlayAnimations(true)
                .build()
            hierarchy.actualImageScaleType = ScalingUtils.ScaleType.FIT_XY
            hierarchy.setProgressBarImage(R.drawable.loading_placeholder_animated)
            hierarchy.setFailureImage(R.drawable.no_image_placeholder)
        }
    }
}