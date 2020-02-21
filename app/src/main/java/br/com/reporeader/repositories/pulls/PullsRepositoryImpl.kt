package br.com.reporeader.repositories.pulls

import br.com.reporeader.data.responses.PullsResponse
import br.com.reporeader.data.responses.RepositoriesResponse
import br.com.reporeader.network.RetrofitInitializer
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PullsRepositoryImpl: PullsRepository {

    override fun getPulls(owner:String, repo:String): Observable<ArrayList<PullsResponse>> {
        return RetrofitInitializer().createInterceptor().getPulls(owner, repo)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}