package com.example.parkseeun.moca_android.ui.community.follow

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.KeyEvent
import android.view.View
import com.example.parkseeun.moca_android.R
import com.example.parkseeun.moca_android.model.get.GetFollowerResponse
import com.example.parkseeun.moca_android.model.get.GetFollowerResponseData
import com.example.parkseeun.moca_android.model.get.GetFollowingResponse
import com.example.parkseeun.moca_android.network.ApplicationController
import com.example.parkseeun.moca_android.util.User
import kotlinx.android.synthetic.main.activity_follow.*
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowActivity : AppCompatActivity() {

    // RecyclerView 설정
    private lateinit var followRecyclerViewAdapter : FollowRecyclerViewAdapter
    var dataList : ArrayList<FollowData> = ArrayList()

    // 통신
    private val networkService  = ApplicationController.instance.networkService
    private lateinit var getFollowerResponse : Call<GetFollowerResponse>
    private lateinit var getFollowingResponse : Call<GetFollowingResponse>
    private lateinit var searchResultList : ArrayList<FollowData>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_follow)
        if(intent.getStringExtra("flag")=="follower") {
            et_follow_search.hint = "팔로워"

            getFollowers()
        }

        else if(intent.getStringExtra("flag")=="following") {
            et_follow_search.hint = "팔로잉"

            getFollowing()
        }

        ib_follow_cancel.setOnClickListener {
            finish()
        }
        searchAction()
    }

    // 검색
    private fun searchAction() {
        et_follow_search.setOnKeyListener(object : View.OnKeyListener {
            override fun onKey(v: View, keyCode: Int, event: KeyEvent): Boolean {
                //Enter key Action
                return if (event.action === KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    searchResultList = ArrayList()

                    var searchString = et_follow_search.text.toString()
                    val regex = Regex(pattern = searchString)

                    for (value in dataList) {
                        if (regex.containsMatchIn(input = value.name)) {
                            searchResultList.add(value)
                        }
                    }

                    followRecyclerViewAdapter = FollowRecyclerViewAdapter(applicationContext!!, searchResultList)
                    rv_follow_list.adapter = followRecyclerViewAdapter
                    rv_follow_list.layoutManager = LinearLayoutManager(applicationContext)

                    true
                } else false
            }
        })
    }

    // 데이터 받아오기 (통신) - 팔로워
    private fun getFollowers() {
        getFollowerResponse = networkService.getFollower(User.token, intent.getStringExtra("user_id")?:User.user_id)
        getFollowerResponse.enqueue(object: Callback<GetFollowerResponse> {
            override fun onFailure(call: Call<GetFollowerResponse>, t: Throwable) {
                toast(t.toString())
            }

            override fun onResponse(call: Call<GetFollowerResponse>, response: Response<GetFollowerResponse>) {
                if(response!!.isSuccessful)
                    if(response!!.body()!!.status==200) {
                        var getFollowerData: ArrayList<GetFollowerResponseData> = response.body()!!.data

                        for (value in getFollowerData) {
                            dataList.add(FollowData(value.user_id, value.user_img_url, value.user_name, value.follow))
                        }
                        setRecyclerView()
                    } else if (response!!.body()!!.status == 204) {
                        toast("팔로워가 존재하지 않습니다!")
                    }
            }
        })
    }

    // 데이터 받아오기 (통신) - 팔로잉
    private fun getFollowing() {
        getFollowingResponse = networkService.getFollowing(User.token, intent.getStringExtra("user_id")?:User.user_id)
        getFollowingResponse.enqueue(object: Callback<GetFollowingResponse> {
            override fun onFailure(call: Call<GetFollowingResponse>, t: Throwable) {
                toast(t.toString())
            }

            override fun onResponse(call: Call<GetFollowingResponse>, response: Response<GetFollowingResponse>) {
                if(response!!.isSuccessful)
                    if(response!!.body()!!.status==200) {
                        var getFollowerData: ArrayList<GetFollowerResponseData> = response.body()!!.data

                        for (value in getFollowerData) {
                            dataList.add(FollowData(value.user_id, value.user_img_url, value.user_name, value.follow))
                        }
                        setRecyclerView()
                    } else if (response!!.body()!!.status == 204) {
                        toast("팔로잉이 존재하지 않습니다!")
                    }
            }
        })
    }

    // RecyclerView 설정
    private fun setRecyclerView() {
        // 임시 데이터
        followRecyclerViewAdapter = FollowRecyclerViewAdapter(applicationContext!!, dataList)
        rv_follow_list.adapter = followRecyclerViewAdapter
        rv_follow_list.layoutManager = LinearLayoutManager(applicationContext)
    }
}