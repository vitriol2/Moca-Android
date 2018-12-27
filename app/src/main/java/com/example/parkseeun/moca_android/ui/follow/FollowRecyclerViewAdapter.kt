package com.example.parkseeun.moca_android.ui.follow

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
import kotlinx.android.synthetic.main.rv_item_follow.view.*

class FollowRecyclerViewAdapter(val context : Context, val dataList : ArrayList<FollowData>) : RecyclerView.Adapter<FollowRecyclerViewAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        // 뷰 인플레이트
        val view : View = LayoutInflater.from(context).inflate(R.layout.rv_item_follow, parent, false)

        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        // 뷰 바인딩
        Glide.with(context).load(dataList[position].imageUrl).into(holder.civ_follow_profile) // 이게 맞을깡
        holder.tv_follow_name.text = dataList[position].name

        // followFlag가 1이면 팔로잉 되어있는 상태라고 가정했당 (1이면 팔로잉, 0이면 팔로잉X)
        if (dataList[position].followFlag == 0) {
            holder.ib_follow_follow.setBackgroundResource(R.drawable.community_following_following)
        }
        else if (dataList[position].followFlag == 1) {
            holder.ib_follow_follow.setBackgroundResource(R.drawable.community_followinglist_follow)
        }

        // 팔로우 버튼 클릭 리스너
        holder.ib_follow_follow.setOnClickListener {
            if (dataList[position].followFlag == 0) {
                holder.ib_follow_follow.setBackgroundResource(R.drawable.community_followinglist_follow)

                dataList[position].followFlag = 1
            }
            else if (dataList[position].followFlag == 1) {
                holder.ib_follow_follow.setBackgroundResource(R.drawable.community_following_following)

                dataList[position].followFlag = 0
            }
        }
    }

    // View Holder
    inner class Holder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val civ_follow_profile : CircleImageView = itemView.findViewById(R.id.civ_follow_profile) as CircleImageView
        val tv_follow_name : TextView = itemView.findViewById(R.id.tv_follow_name) as TextView
        val ib_follow_follow : ImageButton = itemView.findViewById(R.id.ib_follow_follow) as ImageButton
    }
}