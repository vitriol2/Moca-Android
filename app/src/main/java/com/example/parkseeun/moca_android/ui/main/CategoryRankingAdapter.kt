package com.example.parkseeun.moca_android.ui.main

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.parkseeun.moca_android.R
import kotlinx.android.synthetic.main.category_picks_rv_item.view.*

class CategoryRankingAdapter(val ctx : Context, val posts: ArrayList<CategoryRankData>) : RecyclerView.Adapter<CategoryRankingAdapter.Holder>(){
    override fun onCreateViewHolder(container: ViewGroup, p1: Int): CategoryRankingAdapter.Holder {
        val view : View = LayoutInflater.from(ctx).inflate(R.layout.category_ranking_rv_item, container, false)

        return Holder(view)
    }

    override fun getItemCount(): Int = posts.size

    override fun onBindViewHolder(holder: CategoryRankingAdapter.Holder, position: Int) {
        holder.cafeName.text = posts[position].cafename
        holder.location.text = posts[position].location
    }

    inner class Holder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val cafeName : TextView = itemView.findViewById(R.id.tv_cat_ran_rv_item_cafename) as TextView
        val location : TextView = itemView.findViewById(R.id.tv_cat_ran_rv_item_location) as TextView
    }
}