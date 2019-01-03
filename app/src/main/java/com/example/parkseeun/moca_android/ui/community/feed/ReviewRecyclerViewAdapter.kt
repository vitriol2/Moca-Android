package com.example.parkseeun.moca_android.ui.community.feed

import android.content.Context
import android.content.Intent
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView

import com.airbnb.lottie.LottieAnimationView
import com.bumptech.glide.Glide
import com.example.parkseeun.moca_android.R
import com.example.parkseeun.moca_android.ui.community.review_comment.ReviewCommentActivity
import com.example.parkseeun.moca_android.ui.community.review_detail.ReviewDetailActivity
import de.hdodenhof.circleimageview.CircleImageView
import android.graphics.Point
import android.support.v4.view.ViewPager
import android.view.*
import com.example.parkseeun.moca_android.util.ImageAdapter

class ReviewRecyclerViewAdapter(val context : Context, val dataList : ArrayList<ReviewData>) : RecyclerView.Adapter<ReviewRecyclerViewAdapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view : View = LayoutInflater.from(context).inflate(R.layout.rv_item_review, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        // 스크린 너비에 따른 이미지 길이 설정
        val width = getScreenWidth()
        holder.pic.layoutParams.width = (width-dpToPx(41.toFloat())).toInt()
        holder.pic.layoutParams.height = holder.pic.layoutParams.width

        // image viewpager setting
        holder.page.text = "1/10"
        holder.pic.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(p0: Int) {
            }

            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {
            }

            override fun onPageSelected(position: Int) {
                holder.page.text = (position+1).toString()+"/10"
            }
        })
        holder.pic.adapter = ImageAdapter(context, arrayOf("https://avatars1.githubusercontent.com/u/18085486?s=460&v=4", "https://avatars1.githubusercontent.com/u/18085486?s=460&v=4", "https://avatars1.githubusercontent.com/u/18085486?s=460&v=4", "https://avatars1.githubusercontent.com/u/18085486?s=460&v=4", "https://avatars1.githubusercontent.com/u/18085486?s=460&v=4"))

        // view binding
        Glide.with(context).load(dataList[position].profileImg).into(holder.profile)
        holder.name.text = dataList[position].name
        holder.rating.rating = dataList[position].rating.toFloat()
        if(dataList[position].heartFlag==1)
            holder.heart.setBackgroundResource(R.drawable.common_heart_fill)
        else
            holder.heart.setBackgroundResource(R.drawable.common_heart_blank)
        holder.heart.setOnClickListener {
            // 임시 애니메이션
            holder.heartLt.imageAssetsFolder = "/assets"
            holder.heartLt.setAnimation("heart.json")
            holder.heartLt.playAnimation()
        }
        holder.commentBtn.setOnClickListener{
            context.startActivity(Intent(context, ReviewCommentActivity::class.java))
        }
        holder.more.setOnClickListener {
            context.startActivity(Intent(context, ReviewDetailActivity::class.java))
        }
        holder.heartNum.text = dataList[position].heartNum.toString()
        holder.commentNum.text = dataList[position].commentNum.toString()
        holder.cafeName.text = dataList[position].cafeName
        holder.time.text = dataList[position].time
        holder.cafeLocation.text = dataList[position].cafeLocation
        holder.comment.text = dataList[position].comment
    }

    // View Holder
    inner class Holder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val profile : CircleImageView = itemView.findViewById(R.id.review_item_profile_circle) as CircleImageView
        val name : TextView = itemView.findViewById(R.id.review_item_name_tv) as TextView
        val rating : RatingBar = itemView.findViewById(R.id.review_item_rating_rating) as RatingBar
        val heart : ImageView = itemView.findViewById(R.id.review_item_heart_iv) as ImageView
        val commentBtn : ImageView = itemView.findViewById(R.id.review_item_comment_iv) as ImageView
        val heartLt : LottieAnimationView = itemView.findViewById(R.id.review_item_heart_lt) as LottieAnimationView
        val pic : ViewPager = itemView.findViewById(R.id.review_item_pic_vp) as ViewPager
        val heartNum : TextView = itemView.findViewById(R.id.review_item_heart2Num_iv) as TextView
        val commentNum : TextView = itemView.findViewById(R.id.review_item_comment2_tv) as TextView
        val cafeName : TextView = itemView.findViewById(R.id.review_item_cafename_tv) as TextView
        val time : TextView = itemView.findViewById(R.id.review_item_time_tv) as TextView
        val cafeLocation : TextView = itemView.findViewById(R.id.review_item_cafelocation_tv) as TextView
        val comment : TextView = itemView.findViewById(R.id.review_item_comment_tv) as TextView
        val more : ConstraintLayout = itemView.findViewById(R.id.review_item_more_const) as ConstraintLayout
        val page : TextView = itemView.findViewById(R.id.review_item_page_tv) as TextView
    }
    private fun dpToPx(dp:Float):Float{
        return (dp * context.resources.displayMetrics.density)
    }
    private fun getScreenWidth():Int{
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = wm.defaultDisplay
        val size = Point()
        display.getSize(size)
        return size.x
    }
}