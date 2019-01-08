package com.example.parkseeun.moca_android.ui.community.follow

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.parkseeun.moca_android.R
import com.example.parkseeun.moca_android.model.post.PostFollowResponse
import com.example.parkseeun.moca_android.network.ApplicationController
import com.example.parkseeun.moca_android.ui.community.feed.FeedActivity
import com.example.parkseeun.moca_android.ui.community.feed.other_user.OtherUserActivity
import com.example.parkseeun.moca_android.util.User
import de.hdodenhof.circleimageview.CircleImageView
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowRecyclerViewAdapter(val context : Context, val dataList : ArrayList<FollowData>) : RecyclerView.Adapter<FollowRecyclerViewAdapter.Holder>() {

    // 통신
    private val networkService  = ApplicationController.instance.networkService
    private lateinit var postFollowerResponse : Call<PostFollowResponse>

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
        // 이름과 프로필 사진 누르면 본인/타인에 따라 각자의 피드로 이동
        View.OnClickListener {
            if(dataList[position].user_id == User.user_id){
                Intent(context, FeedActivity::class.java).apply { putExtra("myFeed", true); context.startActivity(this) }
            }else{
                Intent(context, OtherUserActivity::class.java).apply { putExtra("user_id", dataList[position].user_id); context.startActivity(this) }
            }
        }.let {
            holder.civ_follow_profile.setOnClickListener(it)
            holder.tv_follow_name.setOnClickListener(it)
        }
        // 본인이 아닌 경우에만 follow 버튼 활성화
        if(dataList[position].user_id == User.user_id) {
            holder.ib_follow_follow.visibility = View.GONE
        } else {
            // followFlag가 1이면 팔로잉 되어있는 상태라고 가정했당 (1이면 팔로잉, 0이면 팔로잉X)
            // background image 설정
            if (!dataList[position].followFlag) {
                holder.ib_follow_follow.setBackgroundResource(R.drawable.community_followinglist_follow)
            } else {
                holder.ib_follow_follow.setBackgroundResource(R.drawable.community_following_following)
            }
            // 팔로우 버튼 클릭 리스너 (통신)
            holder.ib_follow_follow.setOnClickListener {
                if (!dataList[position].followFlag) {
                    holder.ib_follow_follow.setBackgroundResource(R.drawable.community_following_following)

                    followUnfollow(dataList[position].user_id)

                    dataList[position].followFlag = true
                } else {
                    holder.ib_follow_follow.setBackgroundResource(R.drawable.community_followinglist_follow)

                    followUnfollow(dataList[position].user_id)

                    dataList[position].followFlag = false
                }
                notifyDataSetChanged()
            }
        }
    }

    // 팔로우, 언팔로우
    private fun followUnfollow(user_id: String) {
        postFollowerResponse = networkService.postFollow(User.token!!, user_id)
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
        val civ_follow_profile : CircleImageView = itemView.findViewById(R.id.civ_follow_profile) as CircleImageView
        val tv_follow_name : TextView = itemView.findViewById(R.id.tv_follow_name) as TextView
        val ib_follow_follow : ImageButton = itemView.findViewById(R.id.ib_follow_follow) as ImageButton
    }
}