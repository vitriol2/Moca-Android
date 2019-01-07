package com.example.parkseeun.moca_android.ui.category.recyclerview.category

import android.os.Parcel
import android.os.Parcelable

class ButtonData(val id: Int, val off: Int, val on: Int, val name: String, var flag: Boolean) : Parcelable {
    constructor(source: Parcel) : this(
            source.readInt(),
            source.readInt(),
            source.readInt(),
            source.readString(),
            1 == source.readInt()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt(id)
        writeInt(off)
        writeInt(on)
        writeString(name)
        writeInt((if (flag) 1 else 0))
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<ButtonData> = object : Parcelable.Creator<ButtonData> {
            override fun createFromParcel(source: Parcel): ButtonData = ButtonData(source)
            override fun newArray(size: Int): Array<ButtonData?> = arrayOfNulls(size)
        }
    }
}