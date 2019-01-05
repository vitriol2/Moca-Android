package com.example.parkseeun.moca_android.ui.communitySearch

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.parkseeun.moca_android.R
import com.example.parkseeun.moca_android.ui.community.feed.other_user.OtherUserActivity
import com.example.parkseeun.moca_android.ui.detail.DetailActivity
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity

class ComSearAllPopUserAdapter(val context : Context, val dataList : ArrayList<ComSearAllPopData>) : RecyclerView.Adapter<ComSearAllPopUserAdapter.Holder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComSearAllPopUserAdapter.Holder {
        // 뷰 인플레이트
        val view : View = LayoutInflater.from(context).inflate(R.layout.rv_frag_com_sear_all_pop_user_item, parent, false)

        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: ComSearAllPopUserAdapter.Holder, position: Int) {
        holder.item.setOnClickListener {
            context!!.startActivity<OtherUserActivity>()
        }
        Glide.with(context).load(dataList[position].photo).into(holder.image)
        holder.username.text = dataList[position].username

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
    inner class Holder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val item : RelativeLayout = itemView.findViewById(R.id.rl_frag_com_sear_all_pop_user) as RelativeLayout
        val image : ImageView = itemView.findViewById(R.id.iv_rv_frag_com_sear_all_pop_user_photo) as ImageView
        val username : TextView = itemView.findViewById(R.id.tv_rv_frag_com_sear_all_pop_user_name) as TextView
        val follow : ImageButton = itemView.findViewById(R.id.ib_rv_frag_com_sear_all_pop_user_name) as ImageButton
    }
}