package com.android.bjapplication.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.bjapplication.R
import com.android.bjapplication.model.Article
import com.android.bjapplication.util.RecyclerViewItemClickListener
import com.android.bjapplication.util.getAge
import com.android.bjapplication.util.loadImage
import kotlinx.android.synthetic.main.item_article.view.*

class ArticleViewHolder(
    parent: ViewGroup,
    recyclerViewItemClickListener: RecyclerViewItemClickListener
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_article, parent, false)
) {

    init {

        itemView.setOnClickListener {
            recyclerViewItemClickListener.onItemClick(itemView.getTag())
        }
    }

    /**
     * Items might be null if they are not paged in yet. PagedListAdapter will re-bind the
     * ViewHolder when Item is loaded.
     */
    fun bindTo(article: Article) {
        itemView.setTag(article)
        itemView.tvTitle.text = article.title ?: "Title not found"
        itemView.tvDescription.text = article.description ?: "Description not found"
        itemView.tvTime.text = article.publishedAt?.getAge() ?: ""
        itemView.image.loadImage(article.urlToImage)

    }
}