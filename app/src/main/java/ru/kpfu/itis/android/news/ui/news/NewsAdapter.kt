package ru.kpfu.itis.android.news.ui.news

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_news.view.*
import ru.kpfu.itis.android.news.R
import ru.kpfu.itis.android.news.data.entity.Article

class NewsAdapter(private val news: List<Article>) :
    ListAdapter<Article, NewsAdapter.NewsHolder>(NewsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsAdapter.NewsHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        return NewsHolder(view)
    }

    override fun onBindViewHolder(holder: NewsAdapter.NewsHolder, position: Int) {
        holder.bind(news[position].title, news[position].description)
    }

    class NewsDiffCallback : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean = oldItem.title == newItem.title

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean = oldItem == newItem

    }

    class NewsHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {

        fun bind(newsName: String, newsDesc: String) {
            containerView.tv_title_item_news.text = newsName
            containerView.tv_desc_item_news.text = newsDesc
        }
    }
}