package com.hyundeee.app.usersearch.api.client

import com.hyundeee.app.usersearch.api.service.GitHubService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by jeonghyeonji on 2017. 8. 2..
 */
@Singleton
class UserSearchClient @Inject constructor(provideOkHttpClient: OkHttpClient) {

    var baseUrl = "https://api.github.com"
    val githubService: GitHubService

    init {
        val retrofit =  Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(provideOkHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        githubService = retrofit.create(GitHubService::class.java)
    }
}