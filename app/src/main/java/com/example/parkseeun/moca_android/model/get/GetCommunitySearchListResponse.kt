package com.example.parkseeun.moca_android.model.get

import android.os.Parcel
import android.os.Parcelable
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter.writeByte



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

class ReviewData() : Parcelable {
    internal var review_id: Int = 0
    internal var review_img_url: String? = null


    constructor(parcel: Parcel) : this() {
        review_id = parcel.readInt()
        review_img_url = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(review_id)
        parcel.writeString(review_img_url)

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ReviewData> {
        override fun createFromParcel(parcel: Parcel): ReviewData {
            return ReviewData(parcel)
        }

        override fun newArray(size: Int): Array<ReviewData?> {
            return arrayOfNulls(size)
        }
    }
}



class SearchUserData() : Parcelable {
    internal var user_id: String? = null
    internal var user_name: String? = null
    internal var user_status_comment: String? = null
    internal var user_img_url: String? = null
    internal var follow_is: Boolean = false


    constructor(parcel: Parcel) : this() {
        user_id = parcel.readString()
        user_name = parcel.readString()
        user_name = parcel.readString()
        user_name = parcel.readString()
        follow_is = parcel.readInt() == 1
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(user_id)
        parcel.writeString(user_name)
        parcel.writeString(user_status_comment)
        parcel.writeString(user_img_url)
        parcel.writeInt(if(follow_is) 1 else 0)

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SearchUserData> {
        override fun createFromParcel(parcel: Parcel): SearchUserData {
            return SearchUserData(parcel)
        }

        override fun newArray(size: Int): Array<SearchUserData?> {
            return arrayOfNulls(size)
        }
    }
}


