package com.example.parkseeun.moca_android.network

import com.example.parkseeun.moca_android.model.get.GetFollowerResponse
import com.example.parkseeun.moca_android.model.get.GetHomeHotplaceResponse
import com.example.parkseeun.moca_android.model.get.GetMocaplusResponse
import com.example.parkseeun.moca_android.model.get.GetMypageScrapResponse
import com.example.parkseeun.moca_android.model.post.PostLoginData
import com.example.parkseeun.moca_android.model.post.PostLoginResponse
import retrofit2.Call
import retrofit2.http.*

interface NetworkService {
    // <회원가입>

    // <로그인>
    @POST("/login")
    fun postLogin(@Body user: PostLoginData):Call<PostLoginResponse>
    // <홈>
    // 검색
    // 랭킹
    // 모카 플러스
    // 모카 픽스

    // <카테고리>
    // 카페 리스트

    // <카페 상세>

    // <위치>

    // <커뮤니티>
    // 팔로워 조회
    @GET("/user/{user_id}/follower")
    fun getFollower(@Header("token") token: String,
                    @Path("user_id") id: String):Call<GetFollowerResponse>
    // <마이 페이지>

    //<홈-핫플레이스>
    @GET("/hot_place")
    fun getHomeHotplaceResponse(
        @Header("Content-Type") content_type : String,
        @Header("Authorization") token : String
    ) : Call<GetHomeHotplaceResponse>

    //<홈-Moca Plus>: 플러스주제 리스트 조회
    @GET("/plus/{length}")
    fun getMocaplusResponse(
        @Header("Content-Type") content_type : String,
        @Header("Authorization") token : String,
        @Path("user_id") user_id : Int
    ) : Call<GetMocaplusResponse>

    //마이페이지 찜한카페목록
    @GET("/user/scrap")
    fun getMypageScrapResponse(
        @Header("Content-Type") content_type: String,
        @Header("Authorization") token : String
    ) : Call<GetMypageScrapResponse>

}