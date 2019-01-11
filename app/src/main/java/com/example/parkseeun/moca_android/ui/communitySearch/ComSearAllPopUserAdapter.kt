package com.example.parkseeun.moca_android.ui.communitySearch

import android.content.Context
import android.content.Intent
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
import com.example.parkseeun.moca_android.model.get.GetBestUserData
import com.example.parkseeun.moca_android.model.post.PostFollowResponse
import com.example.parkseeun.moca_android.network.ApplicationController
import com.example.parkseeun.moca_android.ui.community.feed.other_user.OtherUserActivity
import com.example.parkseeun.moca_android.ui.detail.DetailActivity
import com.example.parkseeun.moca_android.util.User
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ComSearAllPopUserAdapter(val context : Context, val dataList : ArrayList<GetBestUserData>) : RecyclerView.Adapter<ComSearAllPopUserAdapter.Holder>()  {

    val networkService by lazy{
        ApplicationController.instance.networkService
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComSearAllPopUserAdapter.Holder {
        // 뷰 인플레이트
        val view : View = LayoutInflater.from(context).inflate(R.layout.rv_frag_com_sear_all_pop_user_item, parent, false)

        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: ComSearAllPopUserAdapter.Holder, position: Int) {
        holder.item.setOnClickListener {
            val user_id = dataList[position].user_id
            val intent = Intent(context, OtherUserActivity::class.java)
            intent.putExtra("user_id", user_id)
            context.startActivity(intent)
        }
        Glide.with(context).load(dataList[position].user_img_url).into(holder.image)
        holder.username.text = dataList[position].user_name

        if(dataList[position].user_id == User.user_id) {
            holder.follow.visibility = View.GONE
        }else{
            if (!dataList[position].follow) {
                holder.follow.setBackgroundResource(R.drawable.community_followinglist_follow)
            } else {
                holder.follow.setBackgroundResource(R.drawable.community_following_following)
            }
        }

        holder.follow.setOnClickListener {
            if (!dataList[position].follow) {
                holder.follow.setBackgroundResource(R.drawable.community_following_following)

                followUnfollow(dataList[position].user_id)

                dataList[position].follow = true
            } else {
                holder.follow.setBackgroundResource(R.drawable.community_followinglist_follow)

                followUnfollow(dataList[position].user_id)

                dataList[position].follow = false
            }
            notifyDataSetChanged()
        }

    }
    private fun followUnfollow(user_id: String) {
        val postFollowerResponse = networkService.postFollow(User.token!!, user_id)
        postFollowerResponse.enqueue(object: Callback<PostFollowResponse> {
            override fun onFailure(call: Call<PostFollowResponse>, t: Throwable) {
                context.toast(t.message.toString())
            }

            override fun onResponse(call: Call<PostFollowResponse>, response: Response<PostFollowResponse>) {
                if(response.isSuccessful)
                    if(response.body()!!.status != 200) {
                        context.toast(response.body()!!.status.toString() + " : " + response.body()!!.message)
                    }
            }
        })
    }

    // View Holder
    inner class Holder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val item : RelativeLayout = itemView.findViewById(R.id.rl_frag_com_sear_all_pop_user) as RelativeLayout
        val image : ImageView = itemView.findViewById(R.id.iv_rv_frag_com_sear_all_pop_user_photo) as ImageView
        val username : TextView = itemView.findViewById(R.id.tv_rv_frag_com_sear_all_pop_user_name) as TextView
        val follow : ImageButton = itemView.findViewById(R.id.ib_rv_frag_com_sear_all_pop_user_name) as ImageButton
    }


}