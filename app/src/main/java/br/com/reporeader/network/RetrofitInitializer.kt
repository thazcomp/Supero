package br.com.reporeader.network

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import java.io.IOException
import java.util.concurrent.TimeUnit


class RetrofitInitializer {

    private var retrofit: Retrofit?= null

    fun createInterceptor(): ApiService {
        val client = OkHttpClient.Builder()
            .connectTimeout(30000, TimeUnit.SECONDS)
            .readTimeout(30000,TimeUnit.SECONDS)
            .addInterceptor(object : Interceptor{
                @Throws(IOException::class)
                override fun intercept(chain: Interceptor.Chain): Response {
                    val newRequest = chain.request().newBuilder()
                        .method(chain.request().method(), chain.request().body())
                        .build()
                    return chain.proceed(newRequest)
                }
            }).build()

        retrofit = Retrofit.Builder()
            .client(client)
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        return retrofit!!.create(ApiService::class.java)
    }
}