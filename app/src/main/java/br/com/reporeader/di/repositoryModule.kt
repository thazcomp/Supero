package br.com.reporeader.di

import br.com.reporeader.repositories.main.MainRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {

    single { MainRepositoryImpl() }

}

