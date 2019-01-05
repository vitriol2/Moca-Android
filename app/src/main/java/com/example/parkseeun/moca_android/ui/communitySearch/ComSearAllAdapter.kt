package org.sopt.kclean.Controller

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

import java.util.ArrayList

import com.example.parkseeun.moca_android.R
import com.example.parkseeun.moca_android.ui.communitySearch.ComSearAllData

/**
 * Created by choisunpil on 17/11/2018.
 */

class ComSearAllAdapter(internal var context: Context, internal var dataList: ArrayList<ComSearAllData>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        internal val PERSON = 0
        internal val CAFE = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View
        if (viewType == PERSON) {

            view = LayoutInflater.from(parent.context).inflate(R.layout.rv_act_comm_search_user, parent, false)
            return UserHolder(view)
        } else {
            view = LayoutInflater.from(parent.context).inflate(R.layout.rv_item_search, parent, false)
            return CafeHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        var view = holder.itemView

        if (getItemViewType(position) == CAFE) {
            var viewHolder = CafeHolder(view)

            Glide.with(context).load(dataList[position].cafe_img).into(viewHolder.cafeImage!!)

            viewHolder.cafename!!.text = dataList[position].cafe_name
            viewHolder.cafeLocation!!.text = dataList[position].cafe_location

        } else {
            var viewHolder = UserHolder(view)
            Glide.with(context).load(dataList[position].profile_img).into(viewHolder.photo!!)
            viewHolder.username!!.text = dataList[position].user_id

            if (!dataList[position].user_follow!!) {
                viewHolder.follow!!.setBackgroundResource(R.drawable.community_followinglist_follow)
                viewHolder.follow!!.setOnClickListener {
                    viewHolder.follow!!.setBackgroundResource(R.drawable.community_following_following)

                }
            } else {
                viewHolder.follow!!.setBackgroundResource(R.drawable.community_following_following)
                viewHolder.follow!!.setOnClickListener {
                    viewHolder.follow!!.setBackgroundResource(R.drawable.community_followinglist_follow)
                }
            }

            viewHolder.state!!.text = dataList[position].user_status

        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }

    override fun getItemViewType(position: Int): Int {
        return if (dataList[position].flag === PERSON)
            PERSON
        else
            CAFE
    }

    internal inner class UserHolder(itemView: View) : RecyclerView.ViewHolder //결제 정보
        (itemView) {

        val photo: ImageView? = itemView.findViewById(R.id.iv_comm_search_profile) as ImageView
        val username: TextView? = itemView.findViewById(R.id.tv_rv_act_comm_sear_user_name) as TextView
        val state : TextView? = itemView.findViewById(R.id.tv_act_comm_sear_user_state) as TextView
        val follow : ImageButton?= itemView.findViewById(R.id.ib_act_comm_sear_user_follow) as ImageButton


    }

    internal inner class CafeHolder(itemView: View) : RecyclerView.ViewHolder //날짜 정보
        (itemView) {

        val cafeImage: ImageView? = itemView.findViewById(R.id.civ_search_resultImage)
        val cafename: TextView? = itemView.findViewById(R.id.tv_search_cafeName)
        val cafeLocation: TextView? = itemView.findViewById(R.id.tv_search_cafeLocation)

    }


}