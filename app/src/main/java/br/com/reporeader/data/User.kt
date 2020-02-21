package br.com.reporeader.data

import com.google.gson.annotations.SerializedName

data class User (
    @SerializedName("login")
    val login:String?,

    @SerializedName("id")
    val id:Int?,

    @SerializedName("avatar_url")
    val avatar:String?,

    @SerializedName("url")
    val url:String?,

    @SerializedName("html_url")
    val html:String?
)