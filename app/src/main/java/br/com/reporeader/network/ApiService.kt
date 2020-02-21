package br.com.reporeader.network

import br.com.reporeader.data.responses.PullsResponse
import br.com.reporeader.data.responses.RepositoriesResponse
import io.reactivex.Observable
import retrofit2.http.*

interface ApiService {

    @GET("/search/repositories?q=language:Java&sort=score")
    fun getRepo(@Query("page") page:Int): Observable<RepositoriesResponse>

    @GET("/repos/{owner}/{repository}/pulls?sort=title")
    fun getPulls(@Path("owner") owner:String,
                 @Path("repository") repo:String): Observable<ArrayList<PullsResponse>>

}
