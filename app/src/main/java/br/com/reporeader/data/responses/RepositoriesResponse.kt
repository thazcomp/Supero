package br.com.reporeader.data.responses

import br.com.reporeader.data.Owner
import com.google.gson.annotations.SerializedName

data class RepositoriesResponse (
    @SerializedName("id")
    val id:Int,

    @SerializedName("name")
    val name:String,

    @SerializedName("full_name")
    val fullname:String,

    @SerializedName("private")
    val isPrivate:Boolean,

    @SerializedName("owner")
    val owner: Owner,

    @SerializedName("html_url")
    val url:String,

    @SerializedName("description")
    val desc:String,

    @SerializedName("watchers_count")
    val watchers:Int,

    @SerializedName("forks_count")
    val forks:Int,

    @SerializedName("open_issues_count")
    val issues:Int,

    @SerializedName("license")
    val lincese:String,

    @SerializedName("score")
    val score:Double
    )