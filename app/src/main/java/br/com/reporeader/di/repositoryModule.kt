package br.com.reporeader.di

import br.com.reporeader.repositories.main.MainRepositoryImpl
import br.com.reporeader.repositories.pulls.PullsRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {

    single { MainRepositoryImpl() }
    single { PullsRepositoryImpl() }

}

