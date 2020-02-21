package br.com.reporeader.di

import br.com.reporeader.ui.main.adapters.RepoAdapter
import br.com.reporeader.ui.pulls.adapters.PullsAdapter
import org.koin.dsl.module

val adaptersModule = module {
    factory { RepoAdapter(get(), get()) }
    factory { PullsAdapter(get(), get()) }
}

