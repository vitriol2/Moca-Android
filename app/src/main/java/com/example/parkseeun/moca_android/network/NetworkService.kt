package com.example.parkseeun.moca_android.network

import com.example.parkseeun.moca_android.model.get.*
import com.example.parkseeun.moca_android.model.get.GetCafeListResponse
import com.example.parkseeun.moca_android.model.get.GetFollowerResponse
import com.example.parkseeun.moca_android.model.get.GetFollowingResponse
import com.example.parkseeun.moca_android.model.post.PostFollowResponse
import com.example.parkseeun.moca_android.model.post.PostJoinResponse
import com.example.parkseeun.moca_android.model.post.PostLoginData
import com.example.parkseeun.moca_android.model.post.PostLoginResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface NetworkService {
    // <회원가입> - 수민
    @Multipart
    @POST("/user")
    fun postJoin(@Part("user_id") user_id: RequestBody,
                 @Part("user_password") user_password: RequestBody,
                 @Part("user_name") user_name: RequestBody,
                 @Part("user_phone") user_phone: RequestBody,
                 @Part user_img: MultipartBody.Part?) : Call<PostJoinResponse>

    // <로그인> - 아영
    @POST("/login")
    fun postLogin(@Body user: PostLoginData):Call<PostLoginResponse>

    // <홈>
    //<지원: 홈-핫플레이스>
    @GET("/hot_place")
    fun getHomeHotplaceResponse(
        @Header("Content-Type") content_type : String,
        @Header("Authorization") token : String
    ) : Call<GetHomeHotplaceResponse>

    //<지원: 홈-Moca Plus>: 플러스주제 리스트 조회
    @GET("/plus/{length}")
    fun getMocaplusResponse(
        @Header("Content-Type") content_type : String,
        @Header("Authorization") token : String,
        @Path("user_id") user_id : Int
    ) : Call<GetMocaplusResponse>

    // 검색
    @GET("/search/cafe/{keyword}")
    fun getHomeSearch(
        @Path("keyword") keyword : String
    ) : Call<GetHomeSearchResponse>

    // 검색 - 인기 카페 리스트 조횐
    @GET("/cafe/best/{flag}")
    fun getBestCafeList(
        @Header("Authorization") token : String,
        @Path("flag") flag : Int
    ) : Call<GetBestCafeListResponse>

    // 검색 - 모카 추천 플레이스 조회


    // 랭킹
    // 모카 플러스
    // 모카 픽스

    // <카테고리>
    // 카페 리스트
    @GET("/category/location/{district_id}")
    fun getCafeList(@Path("district_id") district_id: Int,
                    @Query("concept") concept: List<Int>,
                    @Query("menu") menu: List<Int>):Call<GetCafeListResponse>

    // <카페 상세>

    // <위치>

    // <커뮤니티>
    // 소셜 피드 - 아영
    @GET("/feed/social")
    fun getSocialFeed(@Header("Authorization") token: String):Call<GetFeedResponse>

    // 유저 피드 - 아영
    @GET("/feed/user/{user_id}")
    fun getUserFeed(@Header("Authorization") token: String,
                      @Path("user_id") id: String):Call<GetFeedResponse>

    // 팔로워 조회 - 수민
    @GET("/user/{user_id}/follower")
    fun getFollower(@Header("Authorization") token: String,
                    @Path("user_id") id: String):Call<GetFollowerResponse>

    // 팔로잉 조회 - 수민
    @GET("/user/{user_id}/following")
    fun getFollowing(@Header("Authorization") token: String,
                     @Path("user_id") id: String) : Call<GetFollowingResponse>

    // 팔로우, 언팔로우 하기 - 수민
    @POST("/user/{user_id}/follow")
    fun postFollow(
        @Header("Authorization") token: String,
        @Path("user_id") id: String) : Call<PostFollowResponse>

    // <마이 페이지>
    // 적립 내역
    @GET("/membership")
    fun getMembership(@Header("Authorization") token: String):Call<GetMembershipResponse>

    //<지원: 마이페이지 찜한카페목록>
    @GET("/user/scrap")
    fun getMypageScrapResponse(
        @Header("Content-Type") content_type: String,
        @Header("Authorization") token : String
    ) : Call<GetMypageScrapResponse>

    //<지원: 마이페이지 멤버십개수 조회>
    @GET("/membership")
    fun getMypageMembershipResponse(
        @Header("Content-Type") content_type : String,
        @Header("Authorization") token : String
    ) : Call<GetMypageMembershipResponse>

    // 주변카페
}