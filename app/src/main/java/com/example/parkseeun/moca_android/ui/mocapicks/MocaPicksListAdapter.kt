package com.example.parkseeun.moca_android.ui.mocapicks

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.bumptech.glide.Glide
import com.example.parkseeun.moca_android.R
import com.example.parkseeun.moca_android.model.delete.DeleteScrapResponse
import com.example.parkseeun.moca_android.model.get.GetMocaPicksListData
import com.example.parkseeun.moca_android.model.post.PostScrapResponse
import com.example.parkseeun.moca_android.network.ApplicationController
import com.example.parkseeun.moca_android.util.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MocaPicksListAdapter(val context : Context, val dataList : ArrayList<GetMocaPicksListData>) : RecyclerView.Adapter<MocaPicksListAdapter.Holder>() {
    private val networkService  = ApplicationController.instance.networkService
    private lateinit var postScrapResponse:Call<PostScrapResponse> //스크랩 하기
    private lateinit var deleteScrapResponse:Call<DeleteScrapResponse>// 스크랩 취소 하기

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MocaPicksListAdapter.Holder {
        // 뷰 인플레이트
        val view : View = LayoutInflater.from(context).inflate(R.layout.rv_item_mocapicks_list, parent, false)

        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: MocaPicksListAdapter.Holder, position: Int) {
        // 뷰 바인딩
        Glide.with(context).load(dataList[position].evaluated_cafe_img_url).into(holder.iv_mocaPicksList_cafeImage)
        holder.tv_mocaPicksList_cafeName.text = dataList[position].cafe_name
        holder.tv_mocaPicksList_cafeLocation.text = dataList[position].address_district_name
        holder.rating_mocaPicksList.rating = dataList[position].evaluated_cafe_rating.toFloat()


        // 토글 버튼 리스너
        holder.tb_mocaPicksList.isChecked = dataList[position].scrab_is
        holder.tb_mocaPicksList.setOnClickListener {

            /*****
             * 스크랩 통신 보내야됨
             */
            if(holder.tb_mocaPicksList.isChecked)
            {
                PostScrap(holder.tb_mocaPicksList, dataList[position])

            }
            else
            {
                DeleteScrap(holder.tb_mocaPicksList, dataList[position])
            }

        }

        // 아이템 누르면 액티비티 전환
        holder.relative_mocaPicks_rv_item.setOnClickListener {
            val intent : Intent = Intent(context, MocaPicksDetailActivity::class.java)
            intent.putExtra("cafe_id",dataList[position].cafe_id)
            intent.putExtra("scrab_is",dataList[position].scrab_is)
            context.startActivity(intent)
        }
    }


    // View Holder
    inner class Holder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val relative_mocaPicks_rv_item : RelativeLayout = itemView.findViewById(R.id.relative_mocaPicks_rv_item)
        val iv_mocaPicksList_cafeImage : ImageView = itemView.findViewById(R.id.iv_mocaPicksList_cafeImage)
        val tb_mocaPicksList : ToggleButton = itemView.findViewById(R.id.tb_mocaPicksList)
        val tv_mocaPicksList_cafeName : TextView = itemView.findViewById(R.id.tv_mocaPicksList_cafeName)
        val tv_mocaPicksList_cafeLocation : TextView = itemView.findViewById(R.id.tv_mocaPicksList_cafeLocation)
        val rating_mocaPicksList : RatingBar = itemView.findViewById(R.id.rating_mocaPicksList)
    }

    /**
     *스크랩 하기
     *
     */
    private  fun PostScrap(tb_scarb: ToggleButton, data: GetMocaPicksListData)
    {
        postScrapResponse = networkService.postScrap(User.token!!, data.cafe_id!!)
        postScrapResponse.enqueue(object: Callback<PostScrapResponse> {

            override fun onFailure(call: Call<PostScrapResponse>, t: Throwable?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.


            }

            override fun onResponse(call: Call<PostScrapResponse>, response: Response<PostScrapResponse>) {
                if(response.isSuccessful) {
                    if(response.body()!!.status==200) {
                        Log.d("tb" ,"true")
                        tb_scarb.isChecked = true
                        data.scrab_is = true
                    }
                    else {
                        Log.e("error",response.body()!!.status.toString() + " : " + response.body()!!.message)

                    }
                }

            }
        })



    }

    /**
     * 스크랩 취소
     *
     */
    private  fun DeleteScrap(tb_scarb: ToggleButton, data: GetMocaPicksListData)
    {
        deleteScrapResponse = networkService.deleteScrap(User.token!!, data.cafe_id!!)
        deleteScrapResponse.enqueue(object: Callback<DeleteScrapResponse> {

            override fun onFailure(call: Call<DeleteScrapResponse>, t: Throwable?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.


            }

            override fun onResponse(call: Call<DeleteScrapResponse>, response: Response<DeleteScrapResponse>) {
                if(response.isSuccessful) {
                    if(response.body()!!.status==200) {
                        Log.d("tb" ,"false")
                        tb_scarb.isChecked = false
                        data.scrab_is = false
                    }
                    else {
                        Log.e("error",response.body()!!.status.toString() + " : " + response.body()!!.message)
                    }
                }

            }
        })

    }



}