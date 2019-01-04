package com.example.parkseeun.moca_android.ui.communitySearch

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.RelativeLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.parkseeun.moca_android.R
import de.hdodenhof.circleimageview.CircleImageView

class CommunitySearchAdapter(val context : Context, val dataList : ArrayList<CommunitySearchAllData>) : RecyclerView.Adapter<CommunitySearchAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        // 뷰 인플레이트
        val view : View = LayoutInflater.from(context).inflate(R.layout.rv_act_comm_search_all, parent, false)

        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        // 클릭 리스너
        holder.user.setOnClickListener {


        }

        // 뷰 바인딩
        Glide.with(context).load(dataList[position].imageUrl).into(holder.profile)
        holder.username.text = dataList[position].name
        holder.state.text = dataList[position].state

        holder.follow.setOnClickListener {
            if (dataList[position].followFlag == false) {
                holder.follow.setBackgroundResource(R.drawable.community_followinglist_follow)

                dataList[position].followFlag = true
            }
            else if (dataList[position].followFlag == true) {
                holder.follow.setBackgroundResource(R.drawable.community_following_following)

                dataList[position].followFlag = false
            }
        }
    }

    // View Holder
    inner class Holder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val user : RelativeLayout = itemView.findViewById(R.id.rl_rv_act_comm_sear) as RelativeLayout
        val profile : CircleImageView = itemView.findViewById(R.id.iv_comm_search_profile) as CircleImageView
        val username : TextView = itemView.findViewById(R.id.tv_rv_act_comm_sear_all_name) as TextView
        val state : TextView = itemView.findViewById(R.id.tv_act_comm_sear_all_state) as TextView
        val follow : ImageButton = itemView.findViewById(R.id.ib_act_comm_sear_all_follow) as ImageButton
    }
}