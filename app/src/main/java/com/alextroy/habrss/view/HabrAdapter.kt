package com.alextroy.habrss.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alextroy.habrss.R
import com.alextroy.habrss.dto.Entry
import kotlinx.android.synthetic.main.article_list_item.view.*

class HabrAdapter(private var items: List<Entry>?, private val context: Context) :
    RecyclerView.Adapter<HabrAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.article_list_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return items!!.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = items!![position]
        holder.articleTV.text = article.title
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val articleTV = view.article!!
    }

    fun addAll(list: List<Entry>?) {
        items = list
        notifyDataSetChanged()
    }
}