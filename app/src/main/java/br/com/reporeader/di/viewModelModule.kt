package br.com.reporeader.di

import br.com.reporeader.ui.main.MainViewModel
import br.com.reporeader.ui.pulls.PullsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { MainViewModel() }
    viewModel { PullsViewModel() }

}