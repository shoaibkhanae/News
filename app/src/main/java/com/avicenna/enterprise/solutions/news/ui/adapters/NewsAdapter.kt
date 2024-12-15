package com.avicenna.enterprise.solutions.news.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.avicenna.enterprise.solutions.news.R
import com.avicenna.enterprise.solutions.news.data.models.Article
import com.google.android.material.card.MaterialCardView

class NewsAdapter(private val dataset: List<Article>, val type: Int)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class CategoryNewsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var image: ImageView
        var title: TextView
        var author: TextView
        var card: MaterialCardView

        init {
            image = view.findViewById(R.id.iv_news)
            title = view.findViewById(R.id.tv_title)
            author = view.findViewById(R.id.tv_author)
            card = view.findViewById(R.id.cd_category_article)
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
            card.setOnClickListener {
                articleSelectedListener.articleSelected(article)
            }
        }
    }

    inner class LatestNewsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var image: ImageView
        var title: TextView
        var author: TextView
        var card: MaterialCardView

        init {
            image = view.findViewById(R.id.iv_latest_news)
            title = view.findViewById(R.id.tv_latest_title)
            author = view.findViewById(R.id.tv_latest_author)
            card = view.findViewById(R.id.cd_latest_article)
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
            card.setOnClickListener {
                articleSelectedListener.articleSelected(article)
            }
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

    interface ArticleSelectedListener {
        fun articleSelected(article: Article)
    }

    lateinit var articleSelectedListener : ArticleSelectedListener


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