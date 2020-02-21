package br.com.reporeader.ui.pulls

import androidx.lifecycle.ViewModel
import br.com.reporeader.data.responses.PullsResponse
import br.com.reporeader.data.responses.RepositoriesResponse
import br.com.reporeader.repositories.pulls.PullsRepository
import br.com.reporeader.repositories.pulls.PullsRepositoryImpl
import io.reactivex.Observable

class PullsViewModel :ViewModel(){

    private var mRepository: PullsRepository = PullsRepositoryImpl()

    fun getPulls(owner:String, repo:String): Observable<ArrayList<PullsResponse>> {
        return mRepository.getPulls(owner, repo)
    }
}