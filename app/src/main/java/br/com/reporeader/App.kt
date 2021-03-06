package br.com.reporeader

import org.koin.core.context.startKoin
import androidx.multidex.MultiDexApplication
import br.com.reporeader.di.adaptersModule
import br.com.reporeader.di.repositoryModule
import br.com.reporeader.di.viewModelModule
import io.reactivex.internal.functions.Functions
import io.reactivex.plugins.RxJavaPlugins

class App : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        RxJavaPlugins.setErrorHandler(Functions.emptyConsumer())
        startKoin{
            modules(listOf(viewModelModule, repositoryModule, adaptersModule))
        }

    }
}