package br.com.reporeader.repositories.pulls

import br.com.reporeader.data.responses.PullsResponse
import br.com.reporeader.data.responses.RepositoriesResponse
import io.reactivex.Observable

interface PullsRepository {
    fun getPulls(owner:String, repo:String): Observable<ArrayList<PullsResponse>>
}
