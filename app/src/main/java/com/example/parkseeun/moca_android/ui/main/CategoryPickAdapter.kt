package com.example.parkseeun.moca_android.ui.main

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.parkseeun.moca_android.R
import kotlinx.android.synthetic.main.category_picks_rv_item.view.*

class CategoryPickAdapter(val ctx : Context, val posts: ArrayList<String>) : RecyclerView.Adapter<CategoryPickAdapter.Holder>(){
    override fun onCreateViewHolder(container: ViewGroup, p1: Int): CategoryPickAdapter.Holder {
        val view : View = LayoutInflater.from(ctx).inflate(R.layout.category_picks_rv_item, container, false)

        return Holder(view)
    }

    override fun getItemCount(): Int = posts.size

    override fun onBindViewHolder(holder: CategoryPickAdapter.Holder, position: Int) {
        holder.cafename.text = posts[position]
    }

    inner class Holder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val cafename : TextView = itemView.findViewById(R.id.tv_cat_pic_rv_item_cafe_name) as TextView
    }
}