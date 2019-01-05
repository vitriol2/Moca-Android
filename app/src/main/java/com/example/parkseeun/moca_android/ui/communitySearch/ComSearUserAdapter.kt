package com.example.parkseeun.moca_android.ui.communitySearch

import android.content.Context
import android.graphics.drawable.Drawable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.parkseeun.moca_android.R
import de.hdodenhof.circleimageview.CircleImageView

class ComSearUserAdapter(val context: Context, val dataList: ArrayList<ComSearUserData>) :
    RecyclerView.Adapter<ComSearUserAdapter.Holder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        // 뷰 인플레이트
        val view: View = LayoutInflater.from(context).inflate(R.layout.rv_act_comm_search_user, parent, false)

        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        // 뷰 바인딩
        Glide.with(context).load(dataList[position].photo).into(holder.photo) // 이게 맞을깡

        holder.name.text = dataList[position].name
        holder.state.text = dataList[position].state

        if (!dataList[position].follow) {
            holder.follow.setBackgroundResource(R.drawable.community_followinglist_follow)
            holder.follow.setOnClickListener {
                    holder.follow.setBackgroundResource(R.drawable.community_following_following)

            }
        } else {
            holder.follow.setBackgroundResource(R.drawable.community_following_following)
            holder.follow.setOnClickListener {
                holder.follow.setBackgroundResource(R.drawable.community_followinglist_follow)
            }
        }


    }

    // View Holder
    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val photo: CircleImageView = itemView.findViewById(R.id.iv_comm_search_profile) as CircleImageView
        val name: TextView = itemView.findViewById(R.id.tv_rv_act_comm_sear_user_name) as TextView
        val state: TextView = itemView.findViewById(R.id.tv_act_comm_sear_user_state) as TextView
        val follow: ImageButton = itemView.findViewById(R.id.ib_act_comm_sear_user_follow) as ImageButton
    }
}