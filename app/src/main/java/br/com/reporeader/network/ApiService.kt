package br.com.reporeader.network

import br.com.reporeader.data.responses.RepositoriesResponse
import io.reactivex.Observable
import retrofit2.http.*

interface ApiService {

    @GET("/repositories?limit=1&q=language:Java&sort=stars")
    fun getRepo(): Observable<RepositoriesResponse>

}
