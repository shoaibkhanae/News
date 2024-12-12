package com.avicenna.enterprise.solutions.news.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.avicenna.enterprise.solutions.news.R
import com.avicenna.enterprise.solutions.news.data.model.Article

class NewsAdapter(private val dataset: List<Article>, val type: Int)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    inner class CategoryNewsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var image: ImageView
        var title: TextView
        var author: TextView

        init {
            image = view.findViewById(R.id.iv_news)
            title = view.findViewById(R.id.tv_title)
            author = view.findViewById(R.id.tv_author)
        }

        fun bind(article: Article) {
            image.apply {
                load(article.urlToImage) {
                    crossfade(true)
                    placeholder(R.drawable.placeholder)
                    error(R.drawable.news_placeholder)
                }
            }
            title.text = article.title
            author.text = article.author
        }
    }

    inner class LatestNewsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var image: ImageView
        var title: TextView
        var author: TextView

        init {
            image = view.findViewById(R.id.iv_latest_news)
            title = view.findViewById(R.id.tv_latest_title)
            author = view.findViewById(R.id.tv_latest_author)
        }

        fun bind(article: Article) {
            image.apply {
                load(article.urlToImage) {
                    crossfade(true)
                    placeholder(R.drawable.placeholder)
                    error(R.drawable.news_placeholder)
                }
            }
            title.text = article.title
            author.text = article.author
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (type == 1) {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.latest_news_item, parent, false)
            return LatestNewsViewHolder(view)
        }
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.category_news_item, parent, false)
        return CategoryNewsViewHolder(view)
    }


    override fun getItemCount(): Int = dataset.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val current = dataset[position]
        if (type == 1) {
            (holder as LatestNewsViewHolder).bind(current)
        } else {
            (holder as CategoryNewsViewHolder).bind(current)
        }
    }
}