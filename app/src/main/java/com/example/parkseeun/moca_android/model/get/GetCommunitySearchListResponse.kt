package com.example.parkseeun.moca_android.model.get

import android.os.Parcel
import android.os.Parcelable

data class GetCommunitySearchListResponse (
    val status : Int,
    val message : String,
    val data : CommunitySearchData
)

data class CommunitySearchData (
    val popularReviewList: ArrayList<ReviewData>,
    val reviewListOrderByLatest : ArrayList<ReviewData>,
    val searchUserList : ArrayList<SearchUserData>
)

data class ReviewData(
    val review_id: Int,
    val review_img_url: String?) : Parcelable {
    constructor(source: Parcel) : this(
        source.readInt(),
        source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt(review_id)
        writeString(review_img_url)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<ReviewData> = object : Parcelable.Creator<ReviewData> {
            override fun createFromParcel(source: Parcel): ReviewData = ReviewData(source)
            override fun newArray(size: Int): Array<ReviewData?> = arrayOfNulls(size)
        }
    }
}

data class SearchUserData(
    val user_id: String,
    val user_name: String,
    val user_status_comment: String?,
    val follow_is: Boolean
) : Parcelable {
    constructor(source: Parcel) : this(
        source.readString(),
        source.readString(),
        source.readString(),
        1 == source.readInt()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(user_id)
        writeString(user_name)
        writeString(user_status_comment)
        writeInt((if (follow_is) 1 else 0))
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<SearchUserData> = object : Parcelable.Creator<SearchUserData> {
            override fun createFromParcel(source: Parcel): SearchUserData = SearchUserData(source)
            override fun newArray(size: Int): Array<SearchUserData?> = arrayOfNulls(size)
        }
    }
}