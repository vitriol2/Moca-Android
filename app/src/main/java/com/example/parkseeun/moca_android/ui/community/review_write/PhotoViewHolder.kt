package com.example.parkseeun.moca_android.ui.community.review_write


import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import com.example.parkseeun.moca_android.R

class PhotoViewHolder(itemView: View?):RecyclerView.ViewHolder(itemView!!) {
    var review_photo : ImageView = itemView!!.findViewById(R.id.img_photo_review) as ImageView
   // var cancel_btn : ImageView = itemView!!.findViewById(R.id.btn_cancel_photo) as ImageView
}