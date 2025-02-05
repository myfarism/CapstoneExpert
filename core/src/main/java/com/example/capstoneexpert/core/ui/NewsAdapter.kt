package com.example.capstoneexpert.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.capstoneexpert.core.databinding.ItemListNewsBinding
import com.example.capstoneexpert.core.domain.model.News

class NewsAdapter : ListAdapter<News, NewsAdapter.ListViewHolder>(DIFF_CALLBACK) {

    var onItemClick: ((News) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(
            ItemListNewsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = getItem(position)
        holder.bind(data)
    }

    inner class ListViewHolder(private var binding: ItemListNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: News) {
            Glide.with(itemView.context)
                .load(data.urlToImage)
                .into(binding.ivItemImage)
            binding.tvItemTitle.text = data.title
        }

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(getItem(bindingAdapterPosition))
            }
        }
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<News> =
            object : DiffUtil.ItemCallback<News>() {
                override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
                    return oldItem.url == newItem.url
                }

                override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
                    return oldItem == newItem
                }
            }
    }
}