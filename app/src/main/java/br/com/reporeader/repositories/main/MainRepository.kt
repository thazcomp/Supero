package br.com.reporeader.repositories.main

import br.com.reporeader.data.responses.RepositoriesResponse
import io.reactivex.Observable

interface MainRepository {
    fun getRepositories(page:Int): Observable<RepositoriesResponse>
}
