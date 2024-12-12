package com.avicenna.enterprise.solutions.news.ui.adapters.viewholders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.avicenna.enterprise.solutions.news.R
import com.avicenna.enterprise.solutions.news.data.model.Article

class LatestNewsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var image: ImageView
    var title: TextView
    var author: TextView

    init {
        image = view.findViewById(R.id.iv_latest_news)
        title = view.findViewById(R.id.tv_latest_title)
        author = view.findViewById(R.id.tv_latest_author)
    }

    fun bind(article: Article) {
        image.load(article.urlToImage) {
            crossfade(true)
            placeholder(R.drawable.placeholder)
            error(R.drawable.no_image)
        }
        title.text = article.title
        author.text = article.author
    }
}