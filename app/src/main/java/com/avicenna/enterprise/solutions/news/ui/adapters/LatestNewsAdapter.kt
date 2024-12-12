package com.avicenna.enterprise.solutions.news.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.avicenna.enterprise.solutions.news.R
import com.avicenna.enterprise.solutions.news.data.model.Article

class LatestNewsAdapter(private val dataset: List<Article>, val type: Int)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


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