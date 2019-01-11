package com.example.parkseeun.moca_android.ui.community.review_write.adapter


import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.RequestManager
import com.example.parkseeun.moca_android.R
import com.example.parkseeun.moca_android.ui.community.review_write.PhotoViewHolder
import com.example.parkseeun.moca_android.ui.community.review_write.WriteReviewActivity
import com.example.parkseeun.moca_android.ui.community.review_write.data.PhotoData
import kotlinx.android.synthetic.main.activity_community_writereview.view.*
import okhttp3.MultipartBody
import org.jetbrains.anko.textColor

class PhotoAdapter(private var photoItems : ArrayList<PhotoData>, var requestManager: RequestManager,var images :ArrayList<MultipartBody.Part> ,val mView : View) : RecyclerView.Adapter<PhotoViewHolder>() {
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
        if (photoItems.size > 9) {
            mView.img_addreview_image.visibility = View.GONE
        } else {
            mView.img_addreview_image.visibility = View.VISIBLE
        }
        holder.cancel_btn.setOnClickListener{
            photoItems.removeAt(position)
            images.removeAt(position)
            if(images.size<1) //삭제 버튼 눌러서 0장이면 확인버튼 비활성화
            {mView.img_addreview_complete.isEnabled= false
            mView.img_addreview_complete.setBackgroundResource(R.drawable.round_square_lightgray)
            mView.img_addreview_complete.textColor= R.color.dark_gray}

            if (photoItems.size > 9) {
                mView.img_addreview_image.visibility = View.GONE
            } else {
                mView.img_addreview_image.visibility = View.VISIBLE
            }
            notifyDataSetChanged()
        }
    }
}
