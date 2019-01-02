package com.example.parkseeun.moca_android.ui.community.review_write.adapter


import android.app.Activity
import android.app.PendingIntent.getActivity
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.example.parkseeun.moca_android.R
import com.example.parkseeun.moca_android.ui.community.review_write.PhotoViewHolder
import com.example.parkseeun.moca_android.ui.community.review_write.data.PhotoData

class PhotoAdapter(private var photoItems : ArrayList<PhotoData>, var requestManager: RequestManager) : RecyclerView.Adapter<PhotoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val mainView  = LayoutInflater.from(parent.context).inflate(R.layout.item_review_photo,parent,false)
        return PhotoViewHolder(mainView)
    }

    override fun getItemCount(): Int = photoItems.size

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {

        requestManager
            .load(photoItems[position].review_img)
            //.centerCrop()
            .into(holder.review_photo)
      //  holder.cancel_btn.setOnClickListener{
   //         photoItems.removeAt(position)
    //        this.notifyDataSetChanged()
      //  }
    }
}
