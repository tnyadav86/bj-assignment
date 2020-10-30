package com.android.bjapplication.ui

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.bjapplication.R
import com.android.bjapplication.model.Source
import com.android.bjapplication.util.RecyclerViewItemClickListener
import kotlinx.android.synthetic.main.item_source.view.*


class SourceRecyclerViewAdapter(private val recyclerViewItemClickListener: RecyclerViewItemClickListener) : RecyclerView.Adapter<SourceRecyclerViewAdapter.ViewHolder>() {

    private var feedItemList: List<Source> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_source, parent, false)

        return ViewHolder(view)
    }

    fun updateItem(eventList: List<Source>) {
        this.feedItemList = eventList
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val source = feedItemList[position]
        holder.itemView.setTag(source)
        holder.itemView.tvName.text = "Name : ${source.name}"
        holder.itemView.tvCountry.text = "Country : ${source.country}"
        holder.itemView.tvLanguage.text = "Language : ${source.language}"
        holder.itemView.tvCategory.text = "Category : ${source.category}"

    }

    override fun getItemCount(): Int = feedItemList.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        init {
            itemView.setOnClickListener {
                recyclerViewItemClickListener.onItemClick(itemView.tag)
            }
        }
    }
}