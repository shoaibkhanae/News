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

class LatestNewsAdapter(private val dataset: List<Article>)
    : RecyclerView.Adapter<LatestNewsAdapter.LatestNewsViewHolder>(){

    class LatestNewsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var image: ImageView
        var title: TextView
        var author: TextView

        init {
            image = view.findViewById(R.id.iv_latest_news)
            title = view.findViewById(R.id.tv_latest_title)
            author = view.findViewById(R.id.tv_latest_author)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LatestNewsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.latest_news_item, parent, false)
        return LatestNewsViewHolder(view)
    }

    override fun getItemCount(): Int = dataset.size

    override fun onBindViewHolder(holder: LatestNewsViewHolder, position: Int) {
        val current = dataset[position]

        holder.image.load(current.urlToImage) {
            placeholder()
        }
        holder.title.text = current.title
        holder.author.text = current.author
    }
}