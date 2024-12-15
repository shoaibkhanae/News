package com.avicenna.enterprise.solutions.news.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.avicenna.enterprise.solutions.news.R
import com.avicenna.enterprise.solutions.news.data.models.Article


class FavoriteAdapter:
    ListAdapter<Article, FavoriteAdapter.FavoriteViewHolder>(ArticleComparator()) {

        class FavoriteViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var image: ImageView

        init {
            image = view.findViewById(R.id.iv_favorite_news)
        }

        fun bind(article: Article) {
            image.load(article.urlToImage) {
                crossfade(true)
                error(R.drawable.news_placeholder)
                placeholder(R.drawable.placeholder)
            }
        }

        companion object {
            fun create(parent: ViewGroup): FavoriteViewHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.favorite_list_item, parent, false)
                return FavoriteViewHolder(view)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    private var onItemClickListener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.onItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return FavoriteViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)

        holder.itemView.setOnClickListener {
            onItemClickListener?.onItemClick(position)
        }
    }

    class ArticleComparator: DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.id == newItem.id
        }
    }
}