package com.tegar.takehometest.model

import android.os.Parcel
import android.os.Parcelable

data class Home(
    var category: String?,
    var author: String?,
    var authorAvatar: String?,
    var title: String?,
    var description: String?,
    var publishedDate: String?,
    var banner: String?,
    var like: Int?
)