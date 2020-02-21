package br.com.reporeader.data.responses

import br.com.reporeader.data.Repository
import com.google.gson.annotations.SerializedName

data class RepositoriesResponse (
    @SerializedName("items")
    val items:ArrayList<Repository>
    )