package com.example.parkseeun.moca_android.network

import com.example.parkseeun.moca_android.model.get.*
import com.example.parkseeun.moca_android.model.get.GetCafeListResponse
import com.example.parkseeun.moca_android.model.get.GetFollowerResponse
import com.example.parkseeun.moca_android.model.get.GetFollowingResponse
import com.example.parkseeun.moca_android.model.post.*
import com.example.parkseeun.moca_android.model.post.PostJoinData
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
    fun postJoin(
        @Part("user_id") user_id: RequestBody,
        @Part("user_password") user_password: RequestBody,
        @Part("user_name") user_name: RequestBody,
        @Part("user_phone") user_phone: RequestBody,
        @Part user_img: MultipartBody.Part?
    ): Call<PostJoinResponse>

    // <로그인> - 아영
    @POST("/login")
    fun postLogin(@Body user: PostLoginData):Call<PostLoginResponse>

    // <홈>

    // 검색
    // 핫플레이스
    @GET("/hot_place")
    fun getHomeHotplaceResponse(
        @Header("Content-Type") content_type : String,
        @Header("Authorization") token : String
    ) : Call<GetHomeHotplaceResponse>

    // 수민 - 핫플레이스 리스트 조회
    @GET("/cafe/hot_place/{hot_place_id}")
    fun getHotPlaceListResponse(
        @Header("Authorization") token : String,
        @Path("hot_place_id") hot_place_id : Int
    ) : Call<GetHotPlaceListResponse>

    //<지원: 홈-Moca Plus>: 플러스주제 리스트 조회
    @GET("/plus/{length}")
    fun getMocaplusResponse(
        @Path("length") length : Int
    ) : Call<GetMocaplusResponse>

    // 수민 - 모카 플러스 상세 조회
    @GET("/plus/{plus_subject_id}/detail")
    fun getMocaPlusDetail(
        @Path("plus_subject_id") plus_subject_id : Int
    ) : Call<GetMocaPlusDetailResponse>

    // 수민 - 모카 플러스 카페 리스트
    @GET("/plus/{plus_subject_id}/contents")
    fun getMocaPlusDetailCafeList(
        @Header("Authorization") token : String,
        @Path("plus_subject_id") plus_subject_id: Int
    ) : Call<GetMocaPlusDetailCafeListResponse>

    // 검색
    @GET("/search/cafe/{keyword}")
    fun getHomeSearch(
        @Path("keyword") keyword : String
    ) : Call<GetHomeSearchResponse>

    // 수민 - 검색 - 인기 카페 리스트 조회
    @GET("/cafe/best/{flag}")
    fun getBestCafeList(
        @Header("Authorization") token : String,
        @Path("flag") flag : Int
    ) : Call<GetBestCafeListResponse>

    // 수민 - 검색 - 모카 추천 플레이스 조회
    @GET("/hot_place/best")
    fun getMocaRecommenPlace(
    ) : Call<GetMocaRecommendHotPlaceResponse>


    // 랭킹 - 아영
    @GET("/cafe/ranking/{length}")
    fun getRanking(@Header("Authorization") token : String,
                   @Path("length") length : Int): Call<GetRankingResponse>

    // 모카 픽스
    @GET("/cafe/pick/{length}")
    fun getMocaPicksList(
        @Header("Authorization") token : String,
        @Path("length") length : Int
    ) : Call<GetMocaPicksListResponse>

    // 모카 픽스 -




    // <카테고리>
    // 카페 리스트
    @GET("/category/location/{district_id}")
    fun getCafeList(
        @Path("district_id") district_id: Int,
        @Query("concept") concept: List<Int>,
        @Query("menu") menu: List<Int>
    ): Call<GetCafeListResponse>



    // <카페 상세>
    @GET("/cafe/{cafe_id}/detail")
    fun getCafeDetailResponse(
        @Header("Authorization") token : String,
        @Path("cafe_id") cafe_id : Int
    ): Call<GetCafeDetailResponse>

    @GET("/cafe/{cafe_id}/image")
    fun getCafeDetailImageResponse(
        @Header("Authorization") token : String,
        @Path("cafe_id") cafe_id : Int
    ): Call<GetCafeDetailImageResponse>




    // <위치>



    // <커뮤니티>
    // 수민 - 검색 전 이번주 인기 많은 사용자
    @GET("/user/best")
    fun getBestUserList(
        @Header("Authorization") token: String
    ) : Call<GetBestUserResponse>

    // 수민 - 커뮤니티 검색
    @GET("/search/community/{keyword}")
    fun getCommunitySearchResult(
        @Header("Authorization") token: String,
        @Path("keyword") keyword : String
    ) : Call<GetCommunitySearchListResponse>

    // 소셜 피드 - 아영
    @GET("/feed/social")
    fun getSocialFeed(@Header("Authorization") token: String): Call<GetFeedResponse>

    // 유저 피드 - 아영
    @GET("/feed/user/{user_id}")
    fun getUserFeed(
        @Header("Authorization") token: String,
        @Path("user_id") id: String
    ): Call<GetFeedResponse>

    // 유저 정보 - 아영
    @GET("/user/{user_id}")
    fun getUserData(@Header("Authorization") token: String,
                    @Path("user_id") id: String):Call<GetUserDataResponse>

    // 리뷰 상세보기 - 아영
    @GET("/review/{review_id}/detail")
    fun getReviewDetail(@Header("Authorization") token: String,
                        @Path("review_id") id: Int):Call<GetReviewDetailResponse>

    // 리뷰 좋아요 toggle - 아영
    @POST("/review/{review_id}/like")
    fun postReviewLike(@Header("Authorization") token: String,
                       @Path("review_id") id: Int):Call<PostFollowResponse>

    // 리뷰 댓글 조회 - 아영
    @GET("/review/{review_id}/comment")
    fun getReviewComment(@Header("Authorization") token: String,
                        @Path("review_id") id: Int):Call<GetReviewCommentResponse>

    // 팔로워 조회 - 수민
    @GET("/user/{user_id}/follower")
    fun getFollower(
        @Header("Authorization") token: String,
        @Path("user_id") id: String
    ): Call<GetFollowerResponse>

    // 팔로잉 조회 - 수민
    @GET("/user/{user_id}/following")
    fun getFollowing(
        @Header("Authorization") token: String,
        @Path("user_id") id: String
    ): Call<GetFollowingResponse>


    // 팔로우, 언팔로우 하기 - 수민
    @POST("/user/{user_id}/follow")
    fun postFollow(
        @Header("Authorization") token: String,
        @Path("user_id") id: String) : Call<PostFollowResponse>

    // 리뷰 작성 -소희
    @POST("/review")
    fun postReviewWriteResponse(
        @Header("Authorization") token : String,
    @Body postReviewWrite : PostReviewWriteData
    ) : Call<PostReviewWriteResponse>


    //Community Search
    //Community Search - user




    // <마이 페이지>
    // 적립 내역 - 아영
    @GET("/membership")
    fun getMembership(@Header("Authorization") token: String): Call<GetMembershipResponse>

    // <맵>
    // 주변카페 - 소희
    @POST("/cafe/nearbycafe")
    fun postNearByCafeResponse(
        @Header("Authorization") token : String,
        @Body postNearByCafe : PostNearByCafeData
    ) : Call<PostNearByCafeResponse>
    // 주소 검색- 소희
    @GET("v2/local/search/keyword.json")
    fun getLocationList(@Header("Authorization") header: String,
                        @Query("query") query: String): Call<GetLocationListResponse>

    //<지원: 홈-핫플레이스>
    
    //<지원: 마이페이지 찜한카페목록>
    @GET("/user/scrap")
    fun getMypageScrapResponse(
        @Header("Authorization") token: String
    ): Call<GetMypageScrapResponse>

    //<마이페이지 멤버십개수 조회>
    @GET("/membership")
    fun getMypageMembershipResponse(
        @Header("Content-Type") content_type: String,
        @Header("Authorization") token: String
    ): Call<GetMypageMembershipResponse>

    //<마이페이지 쿠폰>
    @GET("/coupon")
    fun getMypageCouponResponse(
        @Header("Authorization") token : String
    ): Call<GetMypageCouponResponse>

    //<마이페이지 프로필조회>
    @GET("/user/{user_id}/mypage")
    fun getMypageEditprofileResponse(
        @Header("Authorization") token : String,
        @Path("user_id") user_id : String
    ): Call<GetMypageEditprofileResponse>

    //<마이페이지 프로필수정>
    @Multipart
    @PUT("/user/mypage")
    fun putMypageEditprofileResponse(
        @Header("Authorization") token : String,
        @Part("user_name") user_name: RequestBody,
        @Part("user_phone") user_phone: RequestBody,
        @Part("user_status_comment") user_status_comment: RequestBody,
        @Part user_img: MultipartBody.Part?
    ): Call<GetMypageEditprofileResponse>



    // 주변카페


}