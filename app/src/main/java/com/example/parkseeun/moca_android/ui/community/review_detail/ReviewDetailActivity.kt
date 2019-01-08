package com.example.parkseeun.moca_android.ui.community.review_detail


import android.graphics.Point
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.bumptech.glide.Glide
import com.example.parkseeun.moca_android.R
import com.example.parkseeun.moca_android.model.get.GetReviewCommentResponse
import com.example.parkseeun.moca_android.model.get.GetReviewDetailResponse
import com.example.parkseeun.moca_android.network.ApplicationController
import com.example.parkseeun.moca_android.ui.community.review_comment.ReviewCommentViewAdapter
import com.example.parkseeun.moca_android.util.ImageAdapter
import com.example.parkseeun.moca_android.util.User
import kotlinx.android.synthetic.main.activity_review_detail.*
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReviewDetailActivity : AppCompatActivity() {
    private val networkService  = ApplicationController.instance.networkService
    private lateinit var getReviewResponse : Call<GetReviewDetailResponse>
    private lateinit var getCommentResponse : Call<GetReviewCommentResponse>
    lateinit var reviewCommentViewAdapter : ReviewCommentViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review_detail)

        // 스크린 너비에 따른 이미지 길이 설정
        review_detail_pic_vp.layoutParams.height = getScreenWidth()
        review_detail_space_view.layoutParams.height = getScreenWidth() - dpToPx(13.toFloat()).toInt()

        // 통신
        communicate()
    }

    private fun communicate() {
        // 리뷰 통신
        getReviewResponse = networkService.getReviewDetail(User.token, intent.getIntExtra("review_id", 1))
        getReviewResponse.enqueue(object : Callback<GetReviewDetailResponse>{
            override fun onFailure(call: Call<GetReviewDetailResponse>?, t: Throwable?) {
                toast(t.toString())
            }

            override fun onResponse(call: Call<GetReviewDetailResponse>?, response: Response<GetReviewDetailResponse>?) {
                if(response!!.isSuccessful)
                    if (response.body()!!.status == 200) {
                        response.body()!!.data.let{
                            review_detail_cafename_tv.text = it.cafe_name
                            review_detail_cafename2_tv.text = it.cafe_name
                            review_detail_location_tv.text = it.cafe_address
                            if(it.like)
                                review_detail_heart_btn.setImageResource(R.drawable.common_heart_fill)
                            else
                                review_detail_heart_btn.setImageResource(R.drawable.common_heart_blank)
                            review_detail_page_tv.text = "1/${it.image.size}"
                            review_detail_pic_vp.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
                                override fun onPageScrollStateChanged(p0: Int) {
                                }

                                override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {
                                }

                                override fun onPageSelected(position: Int) {
                                    review_detail_page_tv.text = "${position+1}/${it.image.size}"
                                }
                            })
                            ArrayList<String>().apply{
                                it.image.forEach { add(it.review_img_url?:"") }
                                review_detail_pic_vp.adapter = ImageAdapter(this@ReviewDetailActivity, toTypedArray())
                            }
                            Glide.with(this@ReviewDetailActivity).load(it.user_img_url).into(review_detail_profile_ci)
                            review_detail_name_tv.text = it.user_name
                            review_detail_rating_rating.rating = it.review_rating.toFloat()
                            review_detail_time_tv.text = it.time
                            review_detail_aline_tv.text = it.review_title
                            review_detail_review_tv.text = it.review_content
                        }
                    } else {
                        toast(response.body()!!.status.toString() + ": " + response.body()!!.message)
                    }
            }
        })
        // 댓글 통신
        getCommentResponse = networkService.getReviewComment(User.token, intent.getIntExtra("review_id", 1))
        getCommentResponse.enqueue(object : Callback<GetReviewCommentResponse>{
            override fun onFailure(call: Call<GetReviewCommentResponse>?, t: Throwable?) {
                toast(t.toString())
            }

            override fun onResponse(call: Call<GetReviewCommentResponse>?, response: Response<GetReviewCommentResponse>?) {
                if(response!!.isSuccessful)
                    if (response.body()!!.status == 200) {
                        review_detail_comments_rv.visibility = View.VISIBLE
                        review_detail_empty_iv.visibility = View.GONE
                        reviewCommentViewAdapter = ReviewCommentViewAdapter(applicationContext, response.body()!!.data)
                        review_detail_comments_rv.adapter = reviewCommentViewAdapter
                        review_detail_comments_rv.layoutManager = LinearLayoutManager(applicationContext)
                    } else if (response.body()!!.status != 204) {
                        toast(response.body()!!.status.toString() + ": " + response.body()!!.message)
                    }
            }

        })
    }

    private fun dpToPx(dp:Float):Float{
        return (dp * this.resources.displayMetrics.density)
    }
    private fun getScreenWidth(): Int {
        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        return size.x
    }
}
