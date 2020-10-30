package com.android.bjapplication.ui

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.android.bjapplication.model.Article
import com.android.bjapplication.util.RecyclerViewItemClickListener

class ArticleRecyclerViewAdapter(private val recyclerViewItemClickListener: RecyclerViewItemClickListener) :
    PagedListAdapter<Article, RecyclerView.ViewHolder>(DATA_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        ArticleViewHolder(parent, recyclerViewItemClickListener)


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ArticleViewHolder).bindTo(getItem(position)!!)
    }

    companion object {
        private val DATA_COMPARATOR = object : DiffUtil.ItemCallback<Article>() {
            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean =
                oldItem.title == newItem.title

            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean =
                oldItem == newItem
        }
    }
}