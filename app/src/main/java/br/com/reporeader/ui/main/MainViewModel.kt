package br.com.reporeader.ui.main

import androidx.lifecycle.ViewModel
import br.com.reporeader.data.responses.RepositoriesResponse
import br.com.reporeader.repositories.main.MainRepository
import br.com.reporeader.repositories.main.MainRepositoryImpl
import io.reactivex.Observable

class MainViewModel :ViewModel(){

    private var mRepository: MainRepository = MainRepositoryImpl()

    fun getRepositories(page:Int): Observable<RepositoriesResponse> {
        return mRepository.getRepositories(page)
    }
}