package com.example.submission1_github_user_page

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserGithub(
    var avatar: String,
    var name: String,
    var username: String,
    var company: String,
    var location: String,
    var repository: Int,
    var follower: Int,
    var following: Int
):Parcelable
