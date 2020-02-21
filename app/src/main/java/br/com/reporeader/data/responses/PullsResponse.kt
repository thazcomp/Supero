package br.com.reporeader.data.responses

import br.com.reporeader.data.User
import com.google.gson.annotations.SerializedName

data class PullsResponse (
    @SerializedName("title")
    val title:String,

    @SerializedName("html_url")
    val htmlUrl:String,

    @SerializedName("user")
    val user: User,

    @SerializedName("body")
    val body:String
)