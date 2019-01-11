package com.example.parkseeun.moca_android.ui.communitySearch

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.RelativeLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.parkseeun.moca_android.R
import com.example.parkseeun.moca_android.model.get.SearchUserData
import com.example.parkseeun.moca_android.model.post.PostFollowResponse
import com.example.parkseeun.moca_android.network.ApplicationController
import com.example.parkseeun.moca_android.ui.community.feed.FeedActivity
import com.example.parkseeun.moca_android.ui.community.feed.other_user.OtherUserActivity
import com.example.parkseeun.moca_android.util.User
import de.hdodenhof.circleimageview.CircleImageView
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ComSearUserAdapter(val context: Context, var dataList: ArrayList<SearchUserData>) :
    RecyclerView.Adapter<ComSearUserAdapter.Holder>() {

    private val networkService  = ApplicationController.instance.networkService
    private lateinit var postFollowerResponse: Call<PostFollowResponse>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        // 뷰 인플레이트
        val view: View = LayoutInflater.from(context).inflate(R.layout.rv_act_comm_search_user, parent, false)

        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        // 뷰 바인딩
        holder.item.setOnClickListener {
            val user_id : String = dataList[position].user_id
            val intent = Intent(context, OtherUserActivity::class.java)
            intent.putExtra("user_id", user_id)
            context.startActivity(intent)
        }

//        Glide.with(context).load(dataList[position].).into(holder.photo) // 이게 맞을깡

        holder.name.text = dataList[position].user_name
        holder.state.text = dataList[position].user_status_comment

        View.OnClickListener {
            if (dataList[position].user_id == User.user_id) {
                Intent(context, FeedActivity::class.java).apply {
                    putExtra(
                        "myFeed",
                        true
                    ); context.startActivity(this)
                }
            } else {
                Intent(context, OtherUserActivity::class.java).apply {
                    putExtra(
                        "user_id",
                        dataList[position].user_id
                    ); context.startActivity(this)
                }
            }
        }.let {
            holder.item.setOnClickListener(it)
        }
        // 본인이 아닌 경우에만 follow 버튼 활성화
        if (dataList[position].user_id == User.user_id) {
            holder.follow.visibility = View.GONE
        } else {
            // follow_is가 1이면 팔로잉 되어있는 상태라고 가정했당 (1이면 팔로잉, 0이면 팔로잉X)
            // background image 설정
            if (!dataList[position].follow_is) {
                holder.follow.setBackgroundResource(R.drawable.community_followinglist_follow)
            } else {
                holder.follow.setBackgroundResource(R.drawable.community_following_following)
            }
            // 팔로우 버튼 클릭 리스너 (통신)
            holder.follow.setOnClickListener {
                if (!dataList[position].follow_is) {
                    holder.follow.setBackgroundResource(R.drawable.community_following_following)

                    followUnfollow(dataList[position].user_id)

                    dataList[position].follow_is = true
                } else {
                    holder.follow.setBackgroundResource(R.drawable.community_followinglist_follow)

                    followUnfollow(dataList[position].user_id)

                    dataList[position].follow_is = false
                }
                notifyDataSetChanged()
            }
        }
    }

    private fun followUnfollow(user_id: String) {
        postFollowerResponse = networkService.postFollow(User.token!!, user_id)
        postFollowerResponse.enqueue(object : Callback<PostFollowResponse> {
            override fun onFailure(call: Call<PostFollowResponse>, t: Throwable) {
                context.toast(t.message.toString())
            }

            override fun onResponse(call: Call<PostFollowResponse>, response: Response<PostFollowResponse>) {
                if (response.isSuccessful)
                    if (response.body()!!.status != 200) {
                        context.toast(response.body()!!.status.toString() + " : " + response.body()!!.message)
                    }
            }
        })
    }


    // View Holder
    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val item: RelativeLayout = itemView.findViewById(R.id.rl_rv_frag_comm_sear_user) as RelativeLayout
        val photo: CircleImageView = itemView.findViewById(R.id.iv_comm_search_profile) as CircleImageView
        val name: TextView = itemView.findViewById(R.id.tv_rv_act_comm_sear_user_name) as TextView
        val state: TextView = itemView.findViewById(R.id.tv_act_comm_sear_user_state) as TextView
        val follow: ImageButton = itemView.findViewById(R.id.ib_act_comm_sear_user_follow) as ImageButton
    }
}