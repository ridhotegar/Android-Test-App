package com.tegar.takehometest.model

import android.os.Parcel
import android.os.Parcelable

data class Profile(
    val name: String?,
    val userAvatar: String?,
    val follower: Int?,
    val following: Int?,
    val numberPost: Int?,
    val income: Int?,
//    val diposting: Array<Diposting>
)

data class Diposting(
    val category: String?,
    val title: String?,
    val publishedDate: String?,
    val like: Int?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(category)
        parcel.writeString(title)
        parcel.writeString(publishedDate)
        parcel.writeValue(like)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Diposting> {
        override fun createFromParcel(parcel: Parcel): Diposting {
            return Diposting(parcel)
        }

        override fun newArray(size: Int): Array<Diposting?> {
            return arrayOfNulls(size)
        }
    }
}