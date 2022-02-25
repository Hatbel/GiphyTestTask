package com.hatbel.giphy.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.gif.GifOptions
import com.bumptech.glide.request.RequestOptions
import com.hatbel.giphy.R
import com.hatbel.giphy.databinding.GiphyItemBinding
import com.hatbel.giphy.model.GiphyImage

class GiphyAdapter(
    diffCallback: DiffUtil.ItemCallback<GiphyImage>,
    private val context: Context
) :
    PagingDataAdapter<GiphyImage, GiphyAdapter.DataViewHolder>(diffCallback) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DataViewHolder {
        return DataViewHolder(
            GiphyItemBinding.inflate(LayoutInflater.from(parent.context)),
            context
        )
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class DataViewHolder(
        private val binding: GiphyItemBinding,
        private val context: Context
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(gif: GiphyImage?) {
            Glide
                .with(context)
                .asGif()
                .apply(RequestOptions().diskCacheStrategy(DiskCacheStrategy.NONE))
                .placeholder(R.drawable.bg_white_drawable)
                .error(R.drawable.ic_launcher_background)
                .load(gif?.images?.fixed_height?.url)
                .into(binding.image)
        }
    }

}

object GiphyComparator : DiffUtil.ItemCallback<GiphyImage>() {
    override fun areItemsTheSame(oldItem: GiphyImage, newItem: GiphyImage): Boolean {
        return oldItem.url == newItem.url
    }

    override fun areContentsTheSame(
        oldItem: GiphyImage,
        newItem: GiphyImage
    ): Boolean {
        return oldItem == newItem
    }
}
