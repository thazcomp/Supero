package br.com.reporeader.repositories.main

import br.com.reporeader.data.responses.RepositoriesResponse
import br.com.reporeader.network.RetrofitInitializer
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainRepositoryImpl: MainRepository {

    override fun getRepositories(): Observable<RepositoriesResponse> {
        return RetrofitInitializer().createService().getRepo()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}