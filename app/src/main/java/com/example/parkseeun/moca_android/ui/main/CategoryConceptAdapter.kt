package com.example.parkseeun.moca_android.ui.main

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.parkseeun.moca_android.R
import kotlinx.android.synthetic.main.category_picks_rv_item.view.*

class CategoryConceptAdapter(val ctx : Context, val posts: ArrayList<String>) : RecyclerView.Adapter<CategoryConceptAdapter.Holder>(){
    override fun onCreateViewHolder(container: ViewGroup, p1: Int): CategoryConceptAdapter.Holder {
        val view : View = LayoutInflater.from(ctx).inflate(R.layout.category_concept_rv_item, container, false)

        return Holder(view)
    }

    override fun getItemCount(): Int = posts.size

    override fun onBindViewHolder(holder: CategoryConceptAdapter.Holder, position: Int) {
        holder.conceptName.text = posts[position]
    }

    inner class Holder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val conceptName : TextView = itemView.findViewById(R.id.tv_cat_con_rv_item_concept_name) as TextView
    }
}